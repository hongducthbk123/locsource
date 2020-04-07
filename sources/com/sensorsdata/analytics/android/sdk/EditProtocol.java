package com.sensorsdata.analytics.android.sdk;

import bolts.MeasurementEvent;
import com.sensorsdata.analytics.android.sdk.Pathfinder.PathElement;
import com.sensorsdata.analytics.android.sdk.ViewVisitor.AddAccessibilityEventVisitor;
import com.sensorsdata.analytics.android.sdk.ViewVisitor.AddTextChangeListener;
import com.sensorsdata.analytics.android.sdk.ViewVisitor.OnEventListener;
import com.sensorsdata.analytics.android.sdk.ViewVisitor.ViewDetectorVisitor;
import com.sensorsdata.analytics.android.sdk.util.JSONUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.http.cookie.ClientCookie;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class EditProtocol {
    private static final List<PathElement> NEVER_MATCH_PATH = Collections.emptyList();
    private static final Class<?>[] NO_PARAMS = new Class[0];
    private static final String TAG = "SA.EProtocol";
    private final ResourceIds mResourceIds;

    public static class BadInstructionsException extends Exception {
        private static final long serialVersionUID = -4062004792184145311L;

        public BadInstructionsException(String message) {
            super(message);
        }

        public BadInstructionsException(String message, Throwable e) {
            super(message, e);
        }
    }

    public static class InapplicableInstructionsException extends BadInstructionsException {
        private static final long serialVersionUID = 3977056710817909104L;

        public InapplicableInstructionsException(String message) {
            super(message);
        }
    }

    public EditProtocol(ResourceIds resourceIds) {
        this.mResourceIds = resourceIds;
    }

    public ViewVisitor readEventBinding(JSONObject source, OnEventListener listener) throws BadInstructionsException {
        try {
            String eventName = source.getString(MeasurementEvent.MEASUREMENT_EVENT_NAME_KEY);
            String eventType = source.getString("event_type");
            int triggerId = source.optInt("trigger_id", -1);
            boolean isDeployed = source.optBoolean("deployed", false);
            JSONArray pathDesc = source.getJSONArray(ClientCookie.PATH_ATTR);
            EventInfo eventInfo = new EventInfo(eventName, eventType, pathDesc.toString(), triggerId, isDeployed);
            List<PathElement> path = readPath(pathDesc, this.mResourceIds);
            if (path.size() == 0) {
                throw new InapplicableInstructionsException("event '" + eventInfo + "' will not be bound to any element in the UI.");
            } else if ("click".equals(eventType)) {
                return new AddAccessibilityEventVisitor(path, 1, eventInfo, listener);
            } else {
                if ("selected".equals(eventType)) {
                    return new AddAccessibilityEventVisitor(path, 4, eventInfo, listener);
                }
                if ("text_changed".equals(eventType)) {
                    return new AddTextChangeListener(path, eventInfo, listener);
                }
                if ("detected".equals(eventType)) {
                    return new ViewDetectorVisitor(path, eventInfo, listener);
                }
                throw new BadInstructionsException("SensorsData can't track event type \"" + eventType + "\"");
            }
        } catch (JSONException e) {
            throw new BadInstructionsException("Can't interpret instructions due to JSONException", e);
        }
    }

    public ViewSnapshot readSnapshotConfig(JSONObject source) throws BadInstructionsException {
        List<PropertyDescription> properties = new ArrayList<>();
        try {
            JSONArray classes = source.getJSONObject("config").getJSONArray("classes");
            for (int classIx = 0; classIx < classes.length(); classIx++) {
                JSONObject classDesc = classes.getJSONObject(classIx);
                Class<?> targetClass = Class.forName(classDesc.getString("name"));
                JSONArray propertyDescs = classDesc.getJSONArray("properties");
                for (int i = 0; i < propertyDescs.length(); i++) {
                    properties.add(readPropertyDescription(targetClass, propertyDescs.getJSONObject(i)));
                }
            }
            return new ViewSnapshot(properties, this.mResourceIds);
        } catch (JSONException e) {
            throw new BadInstructionsException("Can't read snapshot configuration", e);
        } catch (ClassNotFoundException e2) {
            throw new BadInstructionsException("Can't resolve types for snapshot configuration", e2);
        }
    }

    private List<PathElement> readPath(JSONArray pathDesc, ResourceIds idNameToId) throws JSONException {
        int prefix;
        List<PathElement> path = new ArrayList<>();
        for (int i = 0; i < pathDesc.length(); i++) {
            JSONObject targetView = pathDesc.getJSONObject(i);
            String prefixCode = JSONUtils.optionalStringKey(targetView, "prefix");
            String targetViewClass = JSONUtils.optionalStringKey(targetView, "view_class");
            int targetIndex = targetView.optInt("index", -1);
            int targetExplicitId = targetView.optInt("id", -1);
            String targetIdName = JSONUtils.optionalStringKey(targetView, "sa_id_name");
            if ("shortest".equals(prefixCode)) {
                prefix = 1;
            } else if (prefixCode == null) {
                prefix = 0;
            } else {
                SALog.m1608i(TAG, "Unrecognized prefix type \"" + prefixCode + "\". No views will be matched");
                return NEVER_MATCH_PATH;
            }
            Integer targetIdOrNull = reconcileIds(targetExplicitId, targetIdName, idNameToId);
            if (targetIdOrNull == null) {
                return NEVER_MATCH_PATH;
            }
            path.add(new PathElement(prefix, targetViewClass, targetIndex, targetIdOrNull.intValue()));
        }
        return path;
    }

    private Integer reconcileIds(int explicitId, String idName, ResourceIds idNameToId) {
        int idFromName;
        if (idName == null || idName.length() <= 0) {
            idFromName = -1;
        } else if (idNameToId.knownIdName(idName)) {
            idFromName = idNameToId.idFromName(idName);
        } else {
            SALog.m1608i(TAG, "Path element contains an id name not known to the system. No views will be matched.\nMake sure that you're not stripping your packages R class out with proguard.\nid name was \"" + idName + "\"");
            return null;
        }
        if (-1 != idFromName && -1 != explicitId && idFromName != explicitId) {
            SALog.m1608i(TAG, "Path contains both a named and an explicit id, and they don't match. No views will be matched.");
            return null;
        } else if (-1 != idFromName) {
            return Integer.valueOf(idFromName);
        } else {
            return Integer.valueOf(explicitId);
        }
    }

    private PropertyDescription readPropertyDescription(Class<?> targetClass, JSONObject propertyDesc) throws BadInstructionsException {
        String mutatorName;
        try {
            String propName = propertyDesc.getString("name");
            Caller accessor = null;
            if (propertyDesc.has("get")) {
                JSONObject accessorConfig = propertyDesc.getJSONObject("get");
                accessor = new Caller(targetClass, accessorConfig.getString("selector"), NO_PARAMS, Class.forName(accessorConfig.getJSONObject("result").getString("type")));
            }
            if (propertyDesc.has("set")) {
                mutatorName = propertyDesc.getJSONObject("set").getString("selector");
            } else {
                mutatorName = null;
            }
            return new PropertyDescription(propName, targetClass, accessor, mutatorName);
        } catch (NoSuchMethodException e) {
            throw new BadInstructionsException("Can't create property reader", e);
        } catch (JSONException e2) {
            throw new BadInstructionsException("Can't read property JSON", e2);
        } catch (ClassNotFoundException e3) {
            throw new BadInstructionsException("Can't read property JSON, relevant arg/return class not found", e3);
        }
    }
}
