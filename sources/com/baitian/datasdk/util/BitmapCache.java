package com.baitian.datasdk.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.graphics.drawable.StateListDrawable;
import android.view.View;

public class BitmapCache {
    private static BitmapCache instance = new BitmapCache();

    public static class SampleViewUtil extends View {
        public static GradientDrawable mDrawable;

        public SampleViewUtil(Context context) {
            super(context);
        }

        public static GradientDrawable getCorner(int color, int tpye, int apha) {
            mDrawable = new GradientDrawable(Orientation.TL_BR, new int[]{color, color});
            mDrawable.setShape(0);
            mDrawable.setGradientRadius((float) (Math.sqrt(2.0d) * 60.0d));
            if (tpye == 1) {
                mDrawable = setCornerRadii(mDrawable, 12.0f, 12.0f, 0.0f, 0.0f);
            }
            if (tpye == 2) {
                mDrawable = setCornerRadii(mDrawable, 0.0f, 0.0f, 12.0f, 12.0f);
            }
            if (tpye == 3) {
                mDrawable = setCornerRadii(mDrawable, 0.0f, 12.0f, 12.0f, 0.0f);
            }
            if (tpye == 4) {
                mDrawable = setCornerRadii(mDrawable, 12.0f, 0.0f, 0.0f, 12.0f);
            }
            if (tpye == 5) {
                mDrawable = setCornerRadii(mDrawable, 12.0f, 0.0f, 12.0f, 0.0f);
            }
            if (tpye == 6) {
                mDrawable = setCornerRadii(mDrawable, 0.0f, 12.0f, 0.0f, 12.0f);
            }
            if (tpye == 7) {
                mDrawable = setCornerRadii(mDrawable, 12.0f, 12.0f, 12.0f, 12.0f);
            }
            if (apha != 0) {
                mDrawable.setAlpha(apha);
            }
            return mDrawable;
        }

        public static GradientDrawable getGradientCorner(int beginColor, int endColor, int tpye, int apha) {
            mDrawable = new GradientDrawable(Orientation.TOP_BOTTOM, new int[]{beginColor, endColor});
            mDrawable.setShape(0);
            mDrawable.setGradientRadius((float) (Math.sqrt(2.0d) * 60.0d));
            if (tpye == 1) {
                mDrawable = setCornerRadii(mDrawable, 12.0f, 12.0f, 0.0f, 0.0f);
            }
            if (tpye == 2) {
                mDrawable = setCornerRadii(mDrawable, 0.0f, 0.0f, 12.0f, 12.0f);
            }
            if (tpye == 3) {
                mDrawable = setCornerRadii(mDrawable, 0.0f, 12.0f, 12.0f, 0.0f);
            }
            if (tpye == 4) {
                mDrawable = setCornerRadii(mDrawable, 12.0f, 0.0f, 0.0f, 12.0f);
            }
            if (tpye == 5) {
                mDrawable = setCornerRadii(mDrawable, 12.0f, 0.0f, 12.0f, 0.0f);
            }
            if (tpye == 6) {
                mDrawable = setCornerRadii(mDrawable, 0.0f, 12.0f, 0.0f, 12.0f);
            }
            if (tpye == 7) {
                mDrawable = setCornerRadii(mDrawable, 12.0f, 12.0f, 12.0f, 12.0f);
            }
            if (apha != 0) {
                mDrawable.setAlpha(apha);
            }
            return mDrawable;
        }

        static GradientDrawable setCornerRadii(GradientDrawable drawable, float r0, float r1, float r2, float r3) {
            drawable.setCornerRadii(new float[]{r0, r0, r1, r1, r2, r2, r3, r3});
            return drawable;
        }
    }

    private BitmapCache() {
    }

    public static BitmapCache getInstance() {
        return instance;
    }

    public void init(Context ctx) {
    }

    public static Drawable getStateListDrawableColor(int startColor, int endColor, int tpye, int apha) {
        StateListDrawable listDrawable = new StateListDrawable();
        listDrawable.addState(new int[]{16842919}, SampleViewUtil.getGradientCorner(startColor, endColor, tpye, apha));
        listDrawable.addState(new int[]{16842913}, SampleViewUtil.getGradientCorner(startColor, endColor, tpye, apha));
        listDrawable.addState(new int[]{16842910}, SampleViewUtil.getGradientCorner(endColor, startColor, tpye, apha));
        return listDrawable;
    }

    public Drawable getDrawableColor(int PressColor, int NorColor) {
        StateListDrawable listDrawable = new StateListDrawable();
        ColorDrawable colorDrawable = new ColorDrawable(PressColor);
        ColorDrawable colorDrawable2 = new ColorDrawable(NorColor);
        listDrawable.addState(new int[]{16842919}, colorDrawable);
        listDrawable.addState(new int[]{16842913}, colorDrawable);
        listDrawable.addState(new int[]{16842910}, colorDrawable2);
        return listDrawable;
    }

    public static StateListDrawable getGradientCornerListDrawable(Context context, int beginColor, int endColor, int tpye) {
        StateListDrawable listDrawable = new StateListDrawable();
        listDrawable.addState(new int[]{16842919}, SampleViewUtil.getGradientCorner(endColor, beginColor, tpye, 0));
        listDrawable.addState(new int[]{16842913}, SampleViewUtil.getGradientCorner(endColor, beginColor, tpye, 0));
        listDrawable.addState(new int[]{16842910}, SampleViewUtil.getGradientCorner(beginColor, endColor, tpye, 0));
        return listDrawable;
    }

    public static StateListDrawable getStateCornerListDrawable(Context context, int picPressed, int picNormal, int type) {
        StateListDrawable listDrawable = new StateListDrawable();
        listDrawable.addState(new int[]{16842919}, SampleViewUtil.getCorner(picPressed, type, 0));
        listDrawable.addState(new int[]{16842913}, SampleViewUtil.getCorner(picPressed, type, 0));
        listDrawable.addState(new int[]{16842910}, SampleViewUtil.getCorner(picNormal, type, 0));
        return listDrawable;
    }

    public static Bitmap drawViewToBitmap(Bitmap dest, View view, int width, int height, int downSampling, Drawable drawable) {
        float scale = 1.0f / ((float) downSampling);
        int heightCopy = view.getHeight();
        view.layout(0, 0, width, height);
        int bmpWidth = (int) (((float) width) * scale);
        int bmpHeight = (int) (((float) height) * scale);
        if (!(dest != null && dest.getWidth() == bmpWidth && dest.getHeight() == bmpHeight)) {
            dest = Bitmap.createBitmap(bmpWidth, bmpHeight, Config.ARGB_8888);
        }
        Canvas c = new Canvas(dest);
        drawable.setBounds(new Rect(0, 0, width, height));
        drawable.draw(c);
        if (downSampling > 1) {
            c.scale(scale, scale);
        }
        view.draw(c);
        view.layout(0, 0, width, heightCopy);
        return dest;
    }
}
