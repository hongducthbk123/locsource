package org.cocos2dx.lib;

import android.content.Context;
import android.graphics.Typeface;
import java.util.HashMap;
import p004cn.jiguang.net.HttpUtils;

public class Cocos2dxTypefaces {
    private static final HashMap<String, Typeface> sTypefaceCache = new HashMap<>();

    public static synchronized Typeface get(Context context, String assetName) {
        Typeface typeface;
        Typeface typeface2;
        synchronized (Cocos2dxTypefaces.class) {
            if (!sTypefaceCache.containsKey(assetName)) {
                if (assetName.startsWith(HttpUtils.PATHS_SEPARATOR)) {
                    typeface2 = Typeface.createFromFile(assetName);
                } else {
                    typeface2 = Typeface.createFromAsset(context.getAssets(), assetName);
                }
                sTypefaceCache.put(assetName, typeface2);
            }
            typeface = (Typeface) sTypefaceCache.get(assetName);
        }
        return typeface;
    }
}
