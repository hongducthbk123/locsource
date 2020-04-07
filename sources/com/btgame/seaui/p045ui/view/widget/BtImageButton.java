package com.btgame.seaui.p045ui.view.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.support.p003v7.widget.AppCompatTextView;
import android.view.View;
import android.view.View.OnClickListener;
import com.btgame.seasdk.btcore.common.util.BTScreenUtil;

/* renamed from: com.btgame.seaui.ui.view.widget.BtImageButton */
public class BtImageButton extends AppCompatTextView {
    /* access modifiers changed from: private */

    /* renamed from: cd */
    public int f939cd = 1000;
    /* access modifiers changed from: private */
    public long lastClickTime;

    public BtImageButton(Context context) {
        super(context);
        setGravity(17);
    }

    public static StateListDrawable createStateListDrawable(Context context, int bgNormalColor, int bgPressedColor, int bdNormalColor, int bdPressedColor, int radius) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        if (!(bgPressedColor == -1 && bdPressedColor == -1)) {
            GradientDrawable pressedGradientDrawable = new GradientDrawable();
            pressedGradientDrawable.setShape(0);
            if (bgPressedColor != -1) {
                pressedGradientDrawable.setColor(bgPressedColor);
            }
            if (bdPressedColor != -1) {
                pressedGradientDrawable.setStroke(BTScreenUtil.getInstance(context).getHorizontalPX(4.0d), bdPressedColor);
            }
            pressedGradientDrawable.setCornerRadius((float) radius);
            stateListDrawable.addState(new int[]{16842919}, pressedGradientDrawable);
        }
        if (!(bgNormalColor == -1 && bdNormalColor == -1)) {
            GradientDrawable normalGradientDrawable = new GradientDrawable();
            normalGradientDrawable.setShape(0);
            if (bgNormalColor != -1) {
                normalGradientDrawable.setColor(bgNormalColor);
            }
            if (bdNormalColor != -1) {
                normalGradientDrawable.setStroke(BTScreenUtil.getInstance(context).getHorizontalPX(4.0d), bdNormalColor);
            }
            normalGradientDrawable.setCornerRadius((float) radius);
            stateListDrawable.addState(new int[]{-16842919}, normalGradientDrawable);
        }
        return stateListDrawable;
    }

    public void setCd(int cd) {
        this.f939cd = cd;
    }

    public void setOnClickListener(final OnClickListener onClickListener) {
        if (onClickListener == null || this.f939cd <= 0) {
            super.setOnClickListener(onClickListener);
        } else {
            super.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    long curTime = System.currentTimeMillis();
                    if (curTime - BtImageButton.this.lastClickTime > ((long) BtImageButton.this.f939cd)) {
                        BtImageButton.this.lastClickTime = curTime;
                        onClickListener.onClick(view);
                    }
                }
            });
        }
    }

    public BtImageButton setBtBackground(Drawable background) {
        setBackgroundDrawable(background);
        return this;
    }

    public BtImageButton setDrawableLeft(Drawable drawableLeft, boolean textCenterHorizontal, int iconPadding, int iconWidth, int iconHeight) {
        drawableLeft.setBounds(0, 0, iconWidth, iconHeight);
        if (textCenterHorizontal) {
            setGravity(17);
        } else {
            setGravity(16);
        }
        setCompoundDrawablePadding(iconPadding);
        setCompoundDrawables(drawableLeft, null, null, null);
        return this;
    }

    public void addPadding(int left, int top, int right, int bottom) {
        super.setPadding(getPaddingLeft() + left, getPaddingTop() + top, getPaddingRight() + right, getPaddingBottom() + bottom);
    }

    public BtImageButton setText(String text, int textSize, ColorStateList color) {
        setTextSize(0, (float) textSize);
        setTextColor(color);
        setText(text);
        return this;
    }
}
