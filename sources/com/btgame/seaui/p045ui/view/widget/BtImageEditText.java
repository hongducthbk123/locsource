package com.btgame.seaui.p045ui.view.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.support.p003v7.widget.AppCompatEditText;
import android.view.MotionEvent;
import android.widget.TextView;
import com.btgame.seasdk.btcore.common.util.BTResourceUtil;
import com.btgame.seasdk.btcore.common.util.BTScreenUtil;
import com.google.api.client.googleapis.media.MediaHttpDownloader;
import java.lang.reflect.Field;

/* renamed from: com.btgame.seaui.ui.view.widget.BtImageEditText */
public class BtImageEditText extends AppCompatEditText {
    private static Field setCursor;
    private Drawable[] drawableRights;
    private int index = 0;
    private OnDrawableRightClick onDrawableRightClick;

    /* renamed from: com.btgame.seaui.ui.view.widget.BtImageEditText$OnDrawableRightClick */
    public interface OnDrawableRightClick {
        void onClick(BtImageEditText btImageEditText);
    }

    public BtImageEditText(Context context) {
        super(context);
        setImeOptions(MediaHttpDownloader.MAXIMUM_CHUNK_SIZE);
        int drawable = BTResourceUtil.findDrawableIdByName("et_cursor");
        if (-1 != drawable) {
            try {
                if (setCursor == null) {
                    setCursor = TextView.class.getDeclaredField("mCursorDrawableRes");
                }
                setCursor.setAccessible(true);
                setCursor.set(this, Integer.valueOf(drawable));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        setInputType(32);
    }

    public void setBackgroundDrawable(Context context, int bgNormalColor, int bgFocusedColor, int bdNormalColor, int bdFocusedColor, int radius) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        if (!(bgFocusedColor == -1 && bdFocusedColor == -1)) {
            GradientDrawable pressedGradientDrawable = new GradientDrawable();
            pressedGradientDrawable.setShape(0);
            if (bgFocusedColor != -1) {
                pressedGradientDrawable.setColor(bgFocusedColor);
            }
            if (bdFocusedColor != -1) {
                pressedGradientDrawable.setStroke(BTScreenUtil.getInstance(context).getHorizontalPX(4.0d), bdFocusedColor);
            }
            pressedGradientDrawable.setCornerRadius((float) radius);
            stateListDrawable.addState(new int[]{16842908}, pressedGradientDrawable);
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
            stateListDrawable.addState(new int[]{-16842908}, normalGradientDrawable);
        }
        setBackgroundDrawable(stateListDrawable);
    }

    public BtImageEditText setDrawable(Drawable drawableLeft, Drawable[] drawableRights2, int textPadding, int iconPadding, int lIconWidth, int lIconHeight, int rIconWidth, int rIconHeight) {
        if (drawableLeft != null) {
            drawableLeft.setBounds(0, 0, lIconWidth, lIconHeight);
        }
        Drawable drawableRight = null;
        this.drawableRights = drawableRights2;
        if (drawableRights2 != null && drawableRights2.length == 2) {
            drawableRight = drawableRights2[0];
            drawableRight.setBounds(0, 0, rIconWidth, rIconHeight);
        }
        setGravity(16);
        setCompoundDrawablePadding(iconPadding);
        setPadding(textPadding, textPadding / 2, textPadding, textPadding / 2);
        setCompoundDrawables(drawableLeft, null, drawableRight, null);
        return this;
    }

    public boolean onTouchEvent(MotionEvent event) {
        boolean touchable = true;
        if (event.getAction() == 1 && getCompoundDrawables()[2] != null) {
            if (event.getX() <= ((float) (getWidth() - getTotalPaddingRight())) || event.getX() >= ((float) (getWidth() - getPaddingRight()))) {
                touchable = false;
            }
            if (touchable) {
                onDrawableRightClick();
            }
        }
        return super.onTouchEvent(event);
    }

    public void onDrawableRightClick() {
        if (this.drawableRights != null && this.drawableRights.length == 2) {
            this.index = this.index + 1 == 2 ? 0 : 1;
            Drawable[] drawables = getCompoundDrawables();
            Drawable drawableRight = this.drawableRights[this.index];
            drawableRight.setBounds(drawables[2].getBounds());
            setCompoundDrawables(drawables[0], drawables[1], drawableRight, drawables[3]);
        }
        if (this.onDrawableRightClick != null) {
            this.onDrawableRightClick.onClick(this);
        }
    }

    public void setOnDrawableRightClick(OnDrawableRightClick onDrawableRightClick2) {
        this.onDrawableRightClick = onDrawableRightClick2;
    }
}
