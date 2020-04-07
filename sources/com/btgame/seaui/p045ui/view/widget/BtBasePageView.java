package com.btgame.seaui.p045ui.view.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.text.Html;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.btgame.seasdk.btcore.common.util.BTResourceUtil;
import com.btgame.seasdk.btcore.common.util.BTScreenUtil;
import com.btgame.seaui.p045ui.constant.UIidAndtag;
import com.btgame.seaui.p045ui.view.widget.BtImageEditText.OnDrawableRightClick;

/* renamed from: com.btgame.seaui.ui.view.widget.BtBasePageView */
public abstract class BtBasePageView extends LinearLayout {
    private StateListDrawable btBg;
    private int etBdFocusedColor;
    private int etBdNormalColor;
    private int etColor;
    private int etHintColor;
    private int etSize;
    private boolean hasGetBtConfig;
    private boolean hasGetRowConfig;
    protected OnClickListener mClickListener;
    /* access modifiers changed from: private */
    public OnPreDrawListener preDrawListener = null;
    private ColorStateList titleStateColor;
    private ImageView ublogo;
    /* access modifiers changed from: private */
    public float xTranslation = -2.0f;
    /* access modifiers changed from: private */
    public float yTranslation = -2.0f;

    /* access modifiers changed from: protected */
    public abstract void initLayout(Context context);

    public BtBasePageView(Context context, OnClickListener clickListener) {
        super(context);
        this.mClickListener = clickListener;
        LayoutParams layoutParams = new LayoutParams(-1, -1);
        layoutParams.gravity = 17;
        setLayoutParams(layoutParams);
        setOrientation(1);
        setGravity(17);
        addLogoView(context);
        initLayout(context);
    }

    public void addLogoView(Context context) {
        this.ublogo = new ImageView(context);
        this.ublogo.setImageResource(BTResourceUtil.findDrawableIdByName("ublogo"));
        this.ublogo.setLayoutParams(new LayoutParams(BTScreenUtil.getInstance(context).getHorizontalPX(160.0d), BTScreenUtil.getInstance(context).getVerticalPX(160.0d)));
        addView(this.ublogo);
    }

    public void initRowConfig(Context context) {
        if (!this.hasGetRowConfig) {
            this.hasGetRowConfig = true;
            this.etHintColor = BTResourceUtil.findColorByName("et_hint_color");
            this.etColor = BTResourceUtil.findColorByName("et_input_color");
            this.etBdNormalColor = BTResourceUtil.findColorByName("et_bd_normal_color");
            this.etBdFocusedColor = BTResourceUtil.findColorByName("et_bd_focused_color");
            this.etSize = BTScreenUtil.countTextSize(context, 36.0f);
        }
    }

    public void initBtConfig(Context context) {
        if (!this.hasGetBtConfig) {
            this.hasGetBtConfig = true;
            this.titleStateColor = BTResourceUtil.findColorStateListByName("bt_text_color_selector");
            this.btBg = (StateListDrawable) BTResourceUtil.findDrawableByName("btn_noicon_img");
        }
    }

    /* access modifiers changed from: protected */
    public EditText createInputRow(Context context, int rowWidth, int rowMarginTop, String inputHint, Drawable drawableLeft, Drawable[] drawableRights, int textPadding, int iconPadding, int lIconWidth, int lIconHeight, int rIconWidth, int rIconHeight, boolean isPwd) {
        initRowConfig(context);
        BtImageEditText editText = new BtImageEditText(context);
        editText.setBackgroundDrawable(context, -1, -1, this.etBdNormalColor, this.etBdFocusedColor, BTScreenUtil.getInstance(context).getVerticalPX(9.0d));
        editText.setDrawable(drawableLeft, drawableRights, textPadding, iconPadding, lIconWidth, lIconHeight, rIconWidth, rIconHeight);
        LayoutParams layoutParams = new LayoutParams(rowWidth, -2);
        layoutParams.topMargin = rowMarginTop;
        editText.setLayoutParams(layoutParams);
        editText.setHint(inputHint);
        editText.setSingleLine(true);
        editText.setTextColor(this.etColor);
        editText.setTextSize(0, (float) this.etSize);
        editText.setHintTextColor(this.etHintColor);
        editText.setFilters(new InputFilter[]{new LengthFilter(36)});
        if (isPwd) {
            editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
            editText.setOnDrawableRightClick(new OnDrawableRightClick() {
                public void onClick(BtImageEditText et) {
                    int pos = et.getSelectionStart();
                    if (et.getTransformationMethod() instanceof PasswordTransformationMethod) {
                        et.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    } else {
                        et.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    }
                    if (et.isFocused()) {
                        et.setSelection(pos);
                    }
                }
            });
        }
        addView(editText);
        return editText;
    }

    /* access modifiers changed from: protected */
    public void addTitle(Context context, String titlesStr) {
        if (!TextUtils.isEmpty(titlesStr)) {
            TextView titile = new TextView(context);
            titile.setLayoutParams(new LayoutParams(-2, -2));
            titile.setTextColor(BTResourceUtil.findColorByName("tv_titile_color"));
            titile.setTextSize(0, (float) BTScreenUtil.countTextSize(getContext(), 48.0f));
            titile.setTypeface(Typeface.defaultFromStyle(1));
            titile.setText(Html.fromHtml(titlesStr));
            addView(titile);
        }
    }

    /* access modifiers changed from: protected */
    public BtImageButton createBtIconButton(Context context, int textSize, boolean textCenterHorizontal, int textPadding, int iconPadding, int iconWidth, int iconHeight, ColorStateList titleStateColor2, StateListDrawable btBg2, String[] bt_config) {
        BtImageButton opBt = createBtNoIconButton(context, textSize, textPadding, titleStateColor2, btBg2, bt_config);
        if (bt_config.length >= 3 && !TextUtils.isEmpty(bt_config[2])) {
            Drawable drawableLeft = BTResourceUtil.findDrawableByName(bt_config[2]);
            if (drawableLeft != null) {
                opBt.setDrawableLeft(drawableLeft, textCenterHorizontal, iconPadding, iconWidth, iconHeight);
            }
        }
        return opBt;
    }

    /* access modifiers changed from: protected */
    public BtImageButton createBtNoIconButton(Context context, int textSize, int textPadding, ColorStateList titleStateColor2, StateListDrawable btBg2, String[] bt_config) {
        if (titleStateColor2 == null || btBg2 == null) {
            initBtConfig(context);
            if (titleStateColor2 == null) {
                titleStateColor2 = this.titleStateColor;
            }
            if (btBg2 == null) {
                btBg2 = this.btBg;
            }
        }
        BtImageButton opBt = new BtImageButton(context);
        opBt.setBtBackground(btBg2.getConstantState().newDrawable()).setText(bt_config[0], textSize, titleStateColor2);
        if (!TextUtils.isEmpty(bt_config[1])) {
            opBt.setTag(bt_config[1]);
            if (bt_config[1].startsWith(UIidAndtag.TAG_THIRDLOGIN_PREFIX)) {
                opBt.setCd(2000);
            }
        }
        opBt.addPadding(textPadding, textPadding, textPadding, textPadding);
        return opBt;
    }

    /* access modifiers changed from: protected */
    public BtImageButton createBtCenterNoIconButton(Context context, int width, int height, int btMarginTop, int textSize, int textPadding, ColorStateList titleStateColor2, StateListDrawable btBg2, String[] bt_config) {
        BtImageButton opBt = createBtNoIconButton(context, textSize, textPadding, titleStateColor2, btBg2, bt_config);
        LayoutParams opBtLp = new LayoutParams(width, height);
        opBtLp.gravity = 17;
        opBtLp.setMargins(0, btMarginTop, 0, 0);
        opBt.setLayoutParams(opBtLp);
        return opBt;
    }

    /* access modifiers changed from: protected */
    public BtImageButton createDefaultOpButton(Context context, String[] bt_config) {
        int textSize = BTScreenUtil.countTextSize(getContext(), 48.0f);
        int width = BTScreenUtil.getInstance(context).getHorizontalPX(400.0d);
        int height = BTScreenUtil.getInstance(context).getVerticalPX(100.0d);
        BtImageButton opBt = createBtCenterNoIconButton(context, -2, -2, BTScreenUtil.getInstance(context).getHorizontalPX(50.0d), textSize, BTScreenUtil.getInstance(context).getHorizontalPX(10.0d), null, null, bt_config);
        opBt.setMinWidth(width);
        opBt.setMinHeight(height);
        return opBt;
    }

    public ImageView getUblogo() {
        return this.ublogo;
    }

    public void setYTranslation(float fraction) {
        if (-2.0f == this.yTranslation || this.yTranslation != fraction) {
            this.yTranslation = fraction;
            if (getHeight() != 0) {
                setTranslationY(((float) getHeight()) * fraction);
            } else if (this.preDrawListener == null) {
                this.preDrawListener = new OnPreDrawListener() {
                    public boolean onPreDraw() {
                        BtBasePageView.this.getViewTreeObserver().removeOnPreDrawListener(BtBasePageView.this.preDrawListener);
                        BtBasePageView.this.preDrawListener = null;
                        BtBasePageView.this.setYTranslation(BtBasePageView.this.yTranslation);
                        return true;
                    }
                };
                getViewTreeObserver().addOnPreDrawListener(this.preDrawListener);
            }
        }
    }

    public float getYTranslation() {
        return this.yTranslation;
    }

    public void setXTranslation(float fraction) {
        if (-2.0f == this.xTranslation || this.xTranslation != fraction) {
            this.xTranslation = fraction;
            if (getWidth() != 0) {
                setTranslationX(((float) getWidth()) * fraction);
            } else if (this.preDrawListener == null) {
                this.preDrawListener = new OnPreDrawListener() {
                    public boolean onPreDraw() {
                        BtBasePageView.this.getViewTreeObserver().removeOnPreDrawListener(BtBasePageView.this.preDrawListener);
                        BtBasePageView.this.preDrawListener = null;
                        BtBasePageView.this.setXTranslation(BtBasePageView.this.xTranslation);
                        return true;
                    }
                };
                getViewTreeObserver().addOnPreDrawListener(this.preDrawListener);
            }
        }
    }

    public float getXTranslation() {
        return this.xTranslation;
    }
}
