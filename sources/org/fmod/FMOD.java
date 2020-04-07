package org.fmod;

import android.content.Context;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.os.Build.VERSION;
import android.util.Log;

public class FMOD {
    private static Context gContext = null;

    public static void init(Context context) {
        gContext = context;
    }

    public static void close() {
        gContext = null;
    }

    public static boolean checkInit() {
        return gContext != null;
    }

    public static AssetManager getAssetManager() {
        if (gContext != null) {
            return gContext.getAssets();
        }
        return null;
    }

    public static boolean supportsLowLatency() {
        boolean z = false;
        if (gContext != null && VERSION.SDK_INT >= 5) {
            int outputBlockSize = getOutputBlockSize();
            boolean hasSystemFeature = gContext.getPackageManager().hasSystemFeature("android.hardware.audio.low_latency");
            boolean hasSystemFeature2 = gContext.getPackageManager().hasSystemFeature("android.hardware.audio.pro");
            if (outputBlockSize > 0 && outputBlockSize <= 512) {
                z = true;
            }
            Log.i("fmod", "FMOD::supportsLowLatency                 : Low latency = " + hasSystemFeature + ", Pro Audio = " + hasSystemFeature2 + ", Acceptable Block Size = " + z + " (" + outputBlockSize + ")");
        }
        return z;
    }

    public static int getOutputSampleRate() {
        if (gContext != null && VERSION.SDK_INT >= 17) {
            String property = ((AudioManager) gContext.getSystemService("audio")).getProperty("android.media.property.OUTPUT_SAMPLE_RATE");
            if (property != null) {
                return Integer.parseInt(property);
            }
        }
        return 0;
    }

    public static int getOutputBlockSize() {
        if (gContext != null && VERSION.SDK_INT >= 17) {
            String property = ((AudioManager) gContext.getSystemService("audio")).getProperty("android.media.property.OUTPUT_FRAMES_PER_BUFFER");
            if (property != null) {
                return Integer.parseInt(property);
            }
        }
        return 0;
    }
}
