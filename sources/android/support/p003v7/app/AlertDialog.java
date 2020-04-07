package android.support.p003v7.app;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.DialogInterface.OnKeyListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.ArrayRes;
import android.support.annotation.AttrRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.annotation.StringRes;
import android.support.annotation.StyleRes;
import android.support.p003v7.app.AlertController.AlertParams;
import android.support.p003v7.appcompat.C0247R;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

/* renamed from: android.support.v7.app.AlertDialog */
public class AlertDialog extends AppCompatDialog implements DialogInterface {
    static final int LAYOUT_HINT_NONE = 0;
    static final int LAYOUT_HINT_SIDE = 1;
    final AlertController mAlert;

    /* renamed from: android.support.v7.app.AlertDialog$Builder */
    public static class Builder {

        /* renamed from: P */
        private final AlertParams f15P;
        private final int mTheme;

        public Builder(@NonNull Context context) {
            this(context, AlertDialog.resolveDialogTheme(context, 0));
        }

        public Builder(@NonNull Context context, @StyleRes int themeResId) {
            this.f15P = new AlertParams(new ContextThemeWrapper(context, AlertDialog.resolveDialogTheme(context, themeResId)));
            this.mTheme = themeResId;
        }

        @NonNull
        public Context getContext() {
            return this.f15P.mContext;
        }

        public Builder setTitle(@StringRes int titleId) {
            this.f15P.mTitle = this.f15P.mContext.getText(titleId);
            return this;
        }

        public Builder setTitle(@Nullable CharSequence title) {
            this.f15P.mTitle = title;
            return this;
        }

        public Builder setCustomTitle(@Nullable View customTitleView) {
            this.f15P.mCustomTitleView = customTitleView;
            return this;
        }

        public Builder setMessage(@StringRes int messageId) {
            this.f15P.mMessage = this.f15P.mContext.getText(messageId);
            return this;
        }

        public Builder setMessage(@Nullable CharSequence message) {
            this.f15P.mMessage = message;
            return this;
        }

        public Builder setIcon(@DrawableRes int iconId) {
            this.f15P.mIconId = iconId;
            return this;
        }

        public Builder setIcon(@Nullable Drawable icon) {
            this.f15P.mIcon = icon;
            return this;
        }

        public Builder setIconAttribute(@AttrRes int attrId) {
            TypedValue out = new TypedValue();
            this.f15P.mContext.getTheme().resolveAttribute(attrId, out, true);
            this.f15P.mIconId = out.resourceId;
            return this;
        }

        public Builder setPositiveButton(@StringRes int textId, OnClickListener listener) {
            this.f15P.mPositiveButtonText = this.f15P.mContext.getText(textId);
            this.f15P.mPositiveButtonListener = listener;
            return this;
        }

        public Builder setPositiveButton(CharSequence text, OnClickListener listener) {
            this.f15P.mPositiveButtonText = text;
            this.f15P.mPositiveButtonListener = listener;
            return this;
        }

        public Builder setNegativeButton(@StringRes int textId, OnClickListener listener) {
            this.f15P.mNegativeButtonText = this.f15P.mContext.getText(textId);
            this.f15P.mNegativeButtonListener = listener;
            return this;
        }

        public Builder setNegativeButton(CharSequence text, OnClickListener listener) {
            this.f15P.mNegativeButtonText = text;
            this.f15P.mNegativeButtonListener = listener;
            return this;
        }

        public Builder setNeutralButton(@StringRes int textId, OnClickListener listener) {
            this.f15P.mNeutralButtonText = this.f15P.mContext.getText(textId);
            this.f15P.mNeutralButtonListener = listener;
            return this;
        }

        public Builder setNeutralButton(CharSequence text, OnClickListener listener) {
            this.f15P.mNeutralButtonText = text;
            this.f15P.mNeutralButtonListener = listener;
            return this;
        }

        public Builder setCancelable(boolean cancelable) {
            this.f15P.mCancelable = cancelable;
            return this;
        }

        public Builder setOnCancelListener(OnCancelListener onCancelListener) {
            this.f15P.mOnCancelListener = onCancelListener;
            return this;
        }

        public Builder setOnDismissListener(OnDismissListener onDismissListener) {
            this.f15P.mOnDismissListener = onDismissListener;
            return this;
        }

        public Builder setOnKeyListener(OnKeyListener onKeyListener) {
            this.f15P.mOnKeyListener = onKeyListener;
            return this;
        }

        public Builder setItems(@ArrayRes int itemsId, OnClickListener listener) {
            this.f15P.mItems = this.f15P.mContext.getResources().getTextArray(itemsId);
            this.f15P.mOnClickListener = listener;
            return this;
        }

        public Builder setItems(CharSequence[] items, OnClickListener listener) {
            this.f15P.mItems = items;
            this.f15P.mOnClickListener = listener;
            return this;
        }

        public Builder setAdapter(ListAdapter adapter, OnClickListener listener) {
            this.f15P.mAdapter = adapter;
            this.f15P.mOnClickListener = listener;
            return this;
        }

        public Builder setCursor(Cursor cursor, OnClickListener listener, String labelColumn) {
            this.f15P.mCursor = cursor;
            this.f15P.mLabelColumn = labelColumn;
            this.f15P.mOnClickListener = listener;
            return this;
        }

        public Builder setMultiChoiceItems(@ArrayRes int itemsId, boolean[] checkedItems, OnMultiChoiceClickListener listener) {
            this.f15P.mItems = this.f15P.mContext.getResources().getTextArray(itemsId);
            this.f15P.mOnCheckboxClickListener = listener;
            this.f15P.mCheckedItems = checkedItems;
            this.f15P.mIsMultiChoice = true;
            return this;
        }

        public Builder setMultiChoiceItems(CharSequence[] items, boolean[] checkedItems, OnMultiChoiceClickListener listener) {
            this.f15P.mItems = items;
            this.f15P.mOnCheckboxClickListener = listener;
            this.f15P.mCheckedItems = checkedItems;
            this.f15P.mIsMultiChoice = true;
            return this;
        }

        public Builder setMultiChoiceItems(Cursor cursor, String isCheckedColumn, String labelColumn, OnMultiChoiceClickListener listener) {
            this.f15P.mCursor = cursor;
            this.f15P.mOnCheckboxClickListener = listener;
            this.f15P.mIsCheckedColumn = isCheckedColumn;
            this.f15P.mLabelColumn = labelColumn;
            this.f15P.mIsMultiChoice = true;
            return this;
        }

        public Builder setSingleChoiceItems(@ArrayRes int itemsId, int checkedItem, OnClickListener listener) {
            this.f15P.mItems = this.f15P.mContext.getResources().getTextArray(itemsId);
            this.f15P.mOnClickListener = listener;
            this.f15P.mCheckedItem = checkedItem;
            this.f15P.mIsSingleChoice = true;
            return this;
        }

        public Builder setSingleChoiceItems(Cursor cursor, int checkedItem, String labelColumn, OnClickListener listener) {
            this.f15P.mCursor = cursor;
            this.f15P.mOnClickListener = listener;
            this.f15P.mCheckedItem = checkedItem;
            this.f15P.mLabelColumn = labelColumn;
            this.f15P.mIsSingleChoice = true;
            return this;
        }

        public Builder setSingleChoiceItems(CharSequence[] items, int checkedItem, OnClickListener listener) {
            this.f15P.mItems = items;
            this.f15P.mOnClickListener = listener;
            this.f15P.mCheckedItem = checkedItem;
            this.f15P.mIsSingleChoice = true;
            return this;
        }

        public Builder setSingleChoiceItems(ListAdapter adapter, int checkedItem, OnClickListener listener) {
            this.f15P.mAdapter = adapter;
            this.f15P.mOnClickListener = listener;
            this.f15P.mCheckedItem = checkedItem;
            this.f15P.mIsSingleChoice = true;
            return this;
        }

        public Builder setOnItemSelectedListener(OnItemSelectedListener listener) {
            this.f15P.mOnItemSelectedListener = listener;
            return this;
        }

        public Builder setView(int layoutResId) {
            this.f15P.mView = null;
            this.f15P.mViewLayoutResId = layoutResId;
            this.f15P.mViewSpacingSpecified = false;
            return this;
        }

        public Builder setView(View view) {
            this.f15P.mView = view;
            this.f15P.mViewLayoutResId = 0;
            this.f15P.mViewSpacingSpecified = false;
            return this;
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        @Deprecated
        public Builder setView(View view, int viewSpacingLeft, int viewSpacingTop, int viewSpacingRight, int viewSpacingBottom) {
            this.f15P.mView = view;
            this.f15P.mViewLayoutResId = 0;
            this.f15P.mViewSpacingSpecified = true;
            this.f15P.mViewSpacingLeft = viewSpacingLeft;
            this.f15P.mViewSpacingTop = viewSpacingTop;
            this.f15P.mViewSpacingRight = viewSpacingRight;
            this.f15P.mViewSpacingBottom = viewSpacingBottom;
            return this;
        }

        @Deprecated
        public Builder setInverseBackgroundForced(boolean useInverseBackground) {
            this.f15P.mForceInverseBackground = useInverseBackground;
            return this;
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        public Builder setRecycleOnMeasureEnabled(boolean enabled) {
            this.f15P.mRecycleOnMeasure = enabled;
            return this;
        }

        public AlertDialog create() {
            AlertDialog dialog = new AlertDialog(this.f15P.mContext, this.mTheme);
            this.f15P.apply(dialog.mAlert);
            dialog.setCancelable(this.f15P.mCancelable);
            if (this.f15P.mCancelable) {
                dialog.setCanceledOnTouchOutside(true);
            }
            dialog.setOnCancelListener(this.f15P.mOnCancelListener);
            dialog.setOnDismissListener(this.f15P.mOnDismissListener);
            if (this.f15P.mOnKeyListener != null) {
                dialog.setOnKeyListener(this.f15P.mOnKeyListener);
            }
            return dialog;
        }

        public AlertDialog show() {
            AlertDialog dialog = create();
            dialog.show();
            return dialog;
        }
    }

    protected AlertDialog(@NonNull Context context) {
        this(context, 0);
    }

    protected AlertDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, resolveDialogTheme(context, themeResId));
        this.mAlert = new AlertController(getContext(), this, getWindow());
    }

    protected AlertDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        this(context, 0);
        setCancelable(cancelable);
        setOnCancelListener(cancelListener);
    }

    static int resolveDialogTheme(@NonNull Context context, @StyleRes int resid) {
        if (((resid >>> 24) & 255) >= 1) {
            return resid;
        }
        TypedValue outValue = new TypedValue();
        context.getTheme().resolveAttribute(C0247R.attr.alertDialogTheme, outValue, true);
        return outValue.resourceId;
    }

    public Button getButton(int whichButton) {
        return this.mAlert.getButton(whichButton);
    }

    public ListView getListView() {
        return this.mAlert.getListView();
    }

    public void setTitle(CharSequence title) {
        super.setTitle(title);
        this.mAlert.setTitle(title);
    }

    public void setCustomTitle(View customTitleView) {
        this.mAlert.setCustomTitle(customTitleView);
    }

    public void setMessage(CharSequence message) {
        this.mAlert.setMessage(message);
    }

    public void setView(View view) {
        this.mAlert.setView(view);
    }

    public void setView(View view, int viewSpacingLeft, int viewSpacingTop, int viewSpacingRight, int viewSpacingBottom) {
        this.mAlert.setView(view, viewSpacingLeft, viewSpacingTop, viewSpacingRight, viewSpacingBottom);
    }

    /* access modifiers changed from: 0000 */
    @RestrictTo({Scope.LIBRARY_GROUP})
    public void setButtonPanelLayoutHint(int layoutHint) {
        this.mAlert.setButtonPanelLayoutHint(layoutHint);
    }

    public void setButton(int whichButton, CharSequence text, Message msg) {
        this.mAlert.setButton(whichButton, text, null, msg);
    }

    public void setButton(int whichButton, CharSequence text, OnClickListener listener) {
        this.mAlert.setButton(whichButton, text, listener, null);
    }

    public void setIcon(int resId) {
        this.mAlert.setIcon(resId);
    }

    public void setIcon(Drawable icon) {
        this.mAlert.setIcon(icon);
    }

    public void setIconAttribute(int attrId) {
        TypedValue out = new TypedValue();
        getContext().getTheme().resolveAttribute(attrId, out, true);
        this.mAlert.setIcon(out.resourceId);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mAlert.installContent();
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (this.mAlert.onKeyDown(keyCode, event)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (this.mAlert.onKeyUp(keyCode, event)) {
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }
}
