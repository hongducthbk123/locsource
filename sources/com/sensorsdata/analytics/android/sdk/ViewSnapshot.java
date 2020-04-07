package com.sensorsdata.analytics.android.sdk;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.util.Base64OutputStream;
import android.util.DisplayMetrics;
import android.util.JsonWriter;
import android.util.LruCache;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.widget.RelativeLayout;
import com.adjust.sdk.Constants;
import com.google.common.base.Ascii;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.json.JSONObject;

@TargetApi(16)
public class ViewSnapshot {
    private static final int MAX_CLASS_NAME_CACHE_SIZE = 255;
    private static final String TAG = "SA.Snapshot";
    private final ClassNameCache mClassnameCache;
    private String[] mLastImageHashArray = null;
    private final Handler mMainThreadHandler;
    private final List<PropertyDescription> mProperties;
    private final ResourceIds mResourceIds;
    private final RootViewFinder mRootViewFinder;

    private static class CachedBitmap {
        private Bitmap mCached = null;
        private String mImageHash = "";
        private final Paint mPaint = new Paint(2);

        public synchronized void recreate(int width, int height, int destDensity, Bitmap source) {
            if (!(this.mCached != null && this.mCached.getWidth() == width && this.mCached.getHeight() == height)) {
                try {
                    this.mCached = Bitmap.createBitmap(width, height, Config.RGB_565);
                } catch (OutOfMemoryError e) {
                    this.mCached = null;
                }
                if (this.mCached != null) {
                    this.mCached.setDensity(destDensity);
                }
            }
            if (this.mCached != null) {
                new Canvas(this.mCached).drawBitmap(source, 0.0f, 0.0f, this.mPaint);
                try {
                    ByteArrayOutputStream imageByte = new ByteArrayOutputStream();
                    this.mCached.compress(CompressFormat.PNG, 100, imageByte);
                    this.mImageHash = toHex(MessageDigest.getInstance(Constants.MD5).digest(imageByte.toByteArray()));
                } catch (Exception e2) {
                    SALog.m1608i(ViewSnapshot.TAG, "CachedBitmap.recreate;Create image_hash error=" + e2);
                }
            }
            return;
        }

        public synchronized void writeBitmapJSON(CompressFormat format, int quality, OutputStream out) throws IOException {
            if (this.mCached == null || this.mCached.getWidth() == 0 || this.mCached.getHeight() == 0) {
                out.write("null".getBytes());
            } else {
                out.write(34);
                Base64OutputStream imageOut = new Base64OutputStream(out, 2);
                this.mCached.compress(CompressFormat.PNG, 100, imageOut);
                imageOut.flush();
                out.write(34);
            }
        }

        public String getImageHash() {
            return this.mImageHash;
        }

        private String toHex(byte[] ary) {
            String str = "0123456789ABCDEF";
            String ret = "";
            for (int i = 0; i < ary.length; i++) {
                ret = (ret + "0123456789ABCDEF".charAt((ary[i] >> 4) & 15)) + "0123456789ABCDEF".charAt(ary[i] & Ascii.f977SI);
            }
            return ret;
        }
    }

    private static class ClassNameCache extends LruCache<Class<?>, String> {
        public ClassNameCache(int maxSize) {
            super(maxSize);
        }

        /* access modifiers changed from: protected */
        public String create(Class<?> klass) {
            return klass.getCanonicalName();
        }
    }

    private static class RootViewFinder implements Callable<List<RootViewInfo>> {
        private final CachedBitmap mCachedBitmap = new CachedBitmap();
        private final int mClientDensity = 160;
        private final DisplayMetrics mDisplayMetrics = new DisplayMetrics();
        private UIThreadSet<Activity> mLiveActivities;
        private final List<RootViewInfo> mRootViews = new ArrayList();

        public void findInActivities(UIThreadSet<Activity> liveActivities) {
            this.mLiveActivities = liveActivities;
        }

        public List<RootViewInfo> call() throws Exception {
            this.mRootViews.clear();
            for (Activity a : this.mLiveActivities.getAll()) {
                String activityName = a.getClass().getCanonicalName();
                View rootView = a.getWindow().getDecorView().getRootView();
                a.getWindowManager().getDefaultDisplay().getMetrics(this.mDisplayMetrics);
                this.mRootViews.add(new RootViewInfo(activityName, rootView));
            }
            int viewCount = this.mRootViews.size();
            for (int i = 0; i < viewCount; i++) {
                takeScreenshot((RootViewInfo) this.mRootViews.get(i));
            }
            return this.mRootViews;
        }

        private void takeScreenshot(RootViewInfo info) {
            View rootView = info.rootView;
            Bitmap rawBitmap = null;
            try {
                Method createSnapshot = View.class.getDeclaredMethod("createSnapshot", new Class[]{Config.class, Integer.TYPE, Boolean.TYPE});
                createSnapshot.setAccessible(true);
                rawBitmap = (Bitmap) createSnapshot.invoke(rootView, new Object[]{Config.RGB_565, Integer.valueOf(-1), Boolean.valueOf(false)});
            } catch (NoSuchMethodException e) {
                SALog.m1609i(ViewSnapshot.TAG, "Can't call createSnapshot, will use drawCache", e);
            } catch (IllegalArgumentException e2) {
                SALog.m1609i(ViewSnapshot.TAG, "Can't call createSnapshot with arguments", e2);
            } catch (InvocationTargetException e3) {
                SALog.m1609i(ViewSnapshot.TAG, "Exception when calling createSnapshot", e3);
            } catch (IllegalAccessException e4) {
                SALog.m1609i(ViewSnapshot.TAG, "Can't access createSnapshot, using drawCache", e4);
            } catch (ClassCastException e5) {
                SALog.m1609i(ViewSnapshot.TAG, "createSnapshot didn't return a bitmap?", e5);
            }
            Boolean originalCacheState = null;
            if (rawBitmap == null) {
                try {
                    originalCacheState = Boolean.valueOf(rootView.isDrawingCacheEnabled());
                    rootView.setDrawingCacheEnabled(true);
                    rootView.buildDrawingCache(true);
                    rawBitmap = rootView.getDrawingCache();
                } catch (RuntimeException e6) {
                    SALog.m1609i(ViewSnapshot.TAG, "Can't take a bitmap snapshot of view " + rootView + ", skipping for now.", e6);
                }
            }
            float scale = 1.0f;
            if (rawBitmap != null) {
                int rawDensity = rawBitmap.getDensity();
                if (rawDensity != 0) {
                    scale = 160.0f / ((float) rawDensity);
                }
                int rawWidth = rawBitmap.getWidth();
                int rawHeight = rawBitmap.getHeight();
                int destWidth = (int) (((double) (((float) rawBitmap.getWidth()) * scale)) + 0.5d);
                int destHeight = (int) (((double) (((float) rawBitmap.getHeight()) * scale)) + 0.5d);
                if (rawWidth > 0 && rawHeight > 0 && destWidth > 0 && destHeight > 0) {
                    this.mCachedBitmap.recreate(destWidth, destHeight, 160, rawBitmap);
                }
            }
            if (originalCacheState != null && !originalCacheState.booleanValue()) {
                rootView.setDrawingCacheEnabled(false);
            }
            info.scale = scale;
            info.screenshot = this.mCachedBitmap;
        }
    }

    private static class RootViewInfo {
        public final String activityName;
        public final View rootView;
        public float scale = 1.0f;
        public CachedBitmap screenshot = null;

        public RootViewInfo(String activityName2, View rootView2) {
            this.activityName = activityName2;
            this.rootView = rootView2;
        }
    }

    public ViewSnapshot(List<PropertyDescription> properties, ResourceIds resourceIds) {
        this.mProperties = properties;
        this.mResourceIds = resourceIds;
        this.mMainThreadHandler = new Handler(Looper.getMainLooper());
        this.mRootViewFinder = new RootViewFinder();
        this.mClassnameCache = new ClassNameCache(255);
    }

    public void snapshots(UIThreadSet<Activity> liveActivities, OutputStream out) throws IOException {
        this.mRootViewFinder.findInActivities(liveActivities);
        FutureTask<List<RootViewInfo>> infoFuture = new FutureTask<>(this.mRootViewFinder);
        this.mMainThreadHandler.post(infoFuture);
        OutputStreamWriter writer = new OutputStreamWriter(out);
        List emptyList = Collections.emptyList();
        writer.write("[");
        try {
            emptyList = (List) infoFuture.get(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            SALog.m1609i(TAG, "Screenshot interrupted, no screenshot will be sent.", e);
        } catch (TimeoutException e2) {
            SALog.m1609i(TAG, "Screenshot took more than 1 second to be scheduled and executed. No screenshot will be sent.", e2);
        } catch (ExecutionException e3) {
            SALog.m1609i(TAG, "Exception thrown during screenshot attempt", e3);
        }
        int infoCount = emptyList.size();
        for (int i = 0; i < infoCount; i++) {
            RootViewInfo info = (RootViewInfo) emptyList.get(i);
            if (i > 0) {
                writer.write(",");
            }
            if (isSnapShotUpdated(info.screenshot.getImageHash())) {
                writer.write("{");
                writer.write("\"activity\":");
                writer.write(JSONObject.quote(info.activityName));
                writer.write(",");
                writer.write("\"scale\":");
                writer.write(String.format("%s", new Object[]{Float.valueOf(info.scale)}));
                writer.write(",");
                writer.write("\"serialized_objects\":");
                JsonWriter j = new JsonWriter(writer);
                j.beginObject();
                j.name("rootObject").value((long) info.rootView.hashCode());
                j.name("objects");
                snapshotViewHierarchy(j, info.rootView);
                j.endObject();
                j.flush();
                writer.write(",");
                writer.write("\"image_hash\":");
                writer.write(JSONObject.quote(info.screenshot.getImageHash()));
                writer.write(",");
                writer.write("\"screenshot\":");
                writer.flush();
                info.screenshot.writeBitmapJSON(CompressFormat.PNG, 100, out);
                writer.write("}");
            } else {
                writer.write("{}");
            }
        }
        writer.write("]");
        writer.flush();
    }

    private void snapshotViewHierarchy(JsonWriter j, View rootView) throws IOException {
        j.beginArray();
        snapshotView(j, rootView);
        j.endArray();
    }

    private void snapshotView(JsonWriter j, View view) throws IOException {
        j.beginObject();
        j.name("hashCode").value((long) view.hashCode());
        j.name("id").value((long) view.getId());
        j.name("index").value((long) getChildIndex(view.getParent(), view));
        j.name("sa_id_name").value(getResName(view));
        j.name("top").value((long) view.getTop());
        j.name("left").value((long) view.getLeft());
        j.name("width").value((long) view.getWidth());
        j.name("height").value((long) view.getHeight());
        j.name("scrollX").value((long) view.getScrollX());
        j.name("scrollY").value((long) view.getScrollY());
        j.name("visibility").value((long) view.getVisibility());
        float translationX = 0.0f;
        float translationY = 0.0f;
        if (VERSION.SDK_INT >= 11) {
            translationX = view.getTranslationX();
            translationY = view.getTranslationY();
        }
        j.name("translationX").value((double) translationX);
        j.name("translationY").value((double) translationY);
        j.name("classes");
        j.beginArray();
        Class<?> klass = view.getClass();
        do {
            j.value((String) this.mClassnameCache.get(klass));
            klass = klass.getSuperclass();
            if (klass == Object.class) {
                break;
            }
        } while (klass != null);
        j.endArray();
        addProperties(j, view);
        LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof RelativeLayout.LayoutParams) {
            int[] rules = ((RelativeLayout.LayoutParams) layoutParams).getRules();
            j.name("layoutRules");
            j.beginArray();
            for (int rule : rules) {
                j.value((long) rule);
            }
            j.endArray();
        }
        j.name("subviews");
        j.beginArray();
        if (view instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) view;
            int childCount = group.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View child = group.getChildAt(i);
                if (child != null) {
                    j.value((long) child.hashCode());
                }
            }
        }
        j.endArray();
        j.endObject();
        if (view instanceof ViewGroup) {
            ViewGroup group2 = (ViewGroup) view;
            int childCount2 = group2.getChildCount();
            for (int i2 = 0; i2 < childCount2; i2++) {
                View child2 = group2.getChildAt(i2);
                if (child2 != null) {
                    snapshotView(j, child2);
                }
            }
        }
    }

    private void addProperties(JsonWriter j, View v) throws IOException {
        Class<?> viewClass = v.getClass();
        for (PropertyDescription desc : this.mProperties) {
            if (desc.targetClass.isAssignableFrom(viewClass) && desc.accessor != null) {
                Object value = desc.accessor.applyMethod(v);
                if (value != null) {
                    if (value instanceof Number) {
                        j.name(desc.name).value((Number) value);
                    } else if (value instanceof Boolean) {
                        j.name(desc.name).value(((Boolean) value).booleanValue());
                    } else if (value instanceof ColorStateList) {
                        j.name(desc.name).value(Integer.valueOf(((ColorStateList) value).getDefaultColor()));
                    } else if (value instanceof Drawable) {
                        Drawable drawable = (Drawable) value;
                        Rect bounds = drawable.getBounds();
                        j.name(desc.name);
                        j.beginObject();
                        j.name("classes");
                        j.beginArray();
                        for (Class klass = drawable.getClass(); klass != Object.class; klass = klass.getSuperclass()) {
                            j.value(klass.getCanonicalName());
                        }
                        j.endArray();
                        j.name("dimensions");
                        j.beginObject();
                        j.name("left").value((long) bounds.left);
                        j.name("right").value((long) bounds.right);
                        j.name("top").value((long) bounds.top);
                        j.name("bottom").value((long) bounds.bottom);
                        j.endObject();
                        if (drawable instanceof ColorDrawable) {
                            j.name("color").value((long) ((ColorDrawable) drawable).getColor());
                        }
                        j.endObject();
                    } else {
                        j.name(desc.name).value(value.toString());
                    }
                }
            }
        }
    }

    public void updateLastImageHashArray(String lastImageHashList) {
        if (lastImageHashList == null || lastImageHashList.length() <= 0) {
            this.mLastImageHashArray = null;
        } else {
            this.mLastImageHashArray = lastImageHashList.split(",");
        }
    }

    private boolean isSnapShotUpdated(String newImageHash) {
        if (newImageHash == null || newImageHash.length() <= 0 || this.mLastImageHashArray == null || this.mLastImageHashArray.length <= 0) {
            return true;
        }
        for (String temp : this.mLastImageHashArray) {
            if (temp.equals(newImageHash)) {
                return false;
            }
        }
        return true;
    }

    private String getResName(View view) {
        int viewId = view.getId();
        if (-1 == viewId) {
            return null;
        }
        return this.mResourceIds.nameForId(viewId);
    }

    private int getChildIndex(ViewParent parent, View child) {
        if (parent == null || !(parent instanceof ViewGroup)) {
            return -1;
        }
        ViewGroup _parent = (ViewGroup) parent;
        String childIdName = getResName(child);
        String childClassName = (String) this.mClassnameCache.get(child.getClass());
        int index = 0;
        for (int i = 0; i < _parent.getChildCount(); i++) {
            View brother = _parent.getChildAt(i);
            if (Pathfinder.hasClassName(brother, childClassName)) {
                String brotherIdName = getResName(brother);
                if (childIdName == null || childIdName.equals(brotherIdName)) {
                    if (brother == child) {
                        return index;
                    }
                    index++;
                }
            }
        }
        return -1;
    }
}
