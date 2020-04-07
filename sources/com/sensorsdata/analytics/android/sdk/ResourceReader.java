package com.sensorsdata.analytics.android.sdk;

import android.R.drawable;
import android.R.id;
import android.content.Context;
import android.util.SparseArray;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public abstract class ResourceReader implements ResourceIds {
    private static final String TAG = "SA.ResourceReader";
    private final Context mContext;
    private final Map<String, Integer> mIdNameToId = new HashMap();
    private final SparseArray<String> mIdToIdName = new SparseArray<>();

    public static class Drawables extends ResourceReader {
        private final String mResourcePackageName;

        protected Drawables(String resourcePackageName, Context context) {
            super(context);
            this.mResourcePackageName = resourcePackageName;
            initialize();
        }

        /* access modifiers changed from: protected */
        public Class<?> getSystemClass() {
            return drawable.class;
        }

        /* access modifiers changed from: protected */
        public String getLocalClassName(Context context) {
            return this.mResourcePackageName + ".R$drawable";
        }
    }

    public static class Ids extends ResourceReader {
        private final String mResourcePackageName;

        public Ids(String resourcePackageName, Context context) {
            super(context);
            this.mResourcePackageName = resourcePackageName;
            initialize();
        }

        /* access modifiers changed from: protected */
        public Class<?> getSystemClass() {
            return id.class;
        }

        /* access modifiers changed from: protected */
        public String getLocalClassName(Context context) {
            return this.mResourcePackageName + ".R$id";
        }
    }

    /* access modifiers changed from: protected */
    public abstract String getLocalClassName(Context context);

    /* access modifiers changed from: protected */
    public abstract Class<?> getSystemClass();

    protected ResourceReader(Context context) {
        this.mContext = context;
    }

    public boolean knownIdName(String name) {
        return this.mIdNameToId.containsKey(name);
    }

    public int idFromName(String name) {
        return ((Integer) this.mIdNameToId.get(name)).intValue();
    }

    public String nameForId(int id) {
        return (String) this.mIdToIdName.get(id);
    }

    private static void readClassIds(Class<?> platformIdClass, String namespace, Map<String, Integer> namesToIds) {
        Field[] fields;
        String namespacedName;
        try {
            for (Field field : platformIdClass.getFields()) {
                if (Modifier.isStatic(field.getModifiers()) && field.getType() == Integer.TYPE) {
                    String name = field.getName();
                    int value = field.getInt(null);
                    if (namespace == null) {
                        namespacedName = name;
                    } else {
                        namespacedName = namespace + ":" + name;
                    }
                    namesToIds.put(namespacedName, Integer.valueOf(value));
                }
            }
        } catch (IllegalAccessException e) {
            SALog.m1609i(TAG, "Can't read built-in id names from " + platformIdClass.getName(), e);
        }
    }

    /* access modifiers changed from: protected */
    public void initialize() {
        this.mIdNameToId.clear();
        this.mIdToIdName.clear();
        readClassIds(getSystemClass(), "android", this.mIdNameToId);
        String localClassName = getLocalClassName(this.mContext);
        try {
            readClassIds(Class.forName(localClassName), null, this.mIdNameToId);
        } catch (ClassNotFoundException e) {
            SALog.m1608i(TAG, "Can't load names for Android view ids from '" + localClassName + "', ids by name will not be available in the events editor.");
            SALog.m1608i(TAG, "You may be missing a Resources class for your package due to your proguard configuration, or you may be using an applicationId in your build that isn't the same as the package declared in your AndroidManifest.xml file.\nIf you're using proguard, you can fix this issue by adding the following to your proguard configuration:\n\n-keep class **.R$* {\n    <fields>;\n}\n\nIf you're not using proguard, or if your proguard configuration already contains the directive above, you can add the following to your AndroidManifest.xml file to explicitly point the SensorsData library to the appropriate library for your resources class:\n\n<meta-data android:name=\"com.sensorsdata.analytics.android.ResourcePackageName\" android:value=\"YOUR_PACKAGE_NAME\" />\n\nwhere YOUR_PACKAGE_NAME is the same string you use for the \"package\" attribute in your <manifest> tag.");
        }
        for (Entry<String, Integer> idMapping : this.mIdNameToId.entrySet()) {
            this.mIdToIdName.put(((Integer) idMapping.getValue()).intValue(), idMapping.getKey());
        }
    }
}
