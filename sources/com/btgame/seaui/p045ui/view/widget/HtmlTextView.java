package com.btgame.seaui.p045ui.view.widget;

import android.content.Context;
import android.os.Build.VERSION;
import android.support.p003v7.widget.AppCompatTextView;
import android.text.Editable;
import android.text.Html;
import android.text.Html.TagHandler;
import android.text.Layout.Alignment;
import android.text.Spanned;
import android.text.style.AlignmentSpan;
import android.text.style.ClickableSpan;
import android.view.View;
import org.xml.sax.XMLReader;

/* renamed from: com.btgame.seaui.ui.view.widget.HtmlTextView */
public class HtmlTextView extends AppCompatTextView {
    /* access modifiers changed from: private */
    public OnHtmlClickListener mOnHtmlClickListener;

    /* renamed from: com.btgame.seaui.ui.view.widget.HtmlTextView$OnHtmlClickListener */
    public interface OnHtmlClickListener {
        void onClick(String str, String str2);
    }

    public HtmlTextView(Context context) {
        super(context);
    }

    public void setOnHtmlClickListener(OnHtmlClickListener onHtmlClickListener) {
        this.mOnHtmlClickListener = onHtmlClickListener;
    }

    public void setHtml(String html) {
        setHtml(html, null);
    }

    public void setHtml(String html, OnHtmlClickListener onHtmlClickListener) {
        Spanned htmlSpan;
        this.mOnHtmlClickListener = onHtmlClickListener;
        TagHandler handler = new TagHandler() {
            int endTag;
            String flag;
            int startTag;
            String text;

            public void handleTag(boolean opening, String tag, Editable editable, XMLReader xmlReader) {
                if (HtmlTextView.this.mOnHtmlClickListener == null || !tag.toLowerCase().startsWith("click")) {
                    if (!tag.equalsIgnoreCase("center")) {
                        return;
                    }
                    if (opening) {
                        this.startTag = editable.length();
                    } else {
                        handleCenterTag(editable);
                    }
                } else if (opening) {
                    this.startTag = editable.length();
                } else {
                    handleClickTag(tag, editable);
                }
            }

            private void handleCenterTag(Editable editable) {
                this.endTag = editable.length();
                this.text = editable.subSequence(this.startTag, this.endTag).toString();
                editable.setSpan(new AlignmentSpan() {
                    public Alignment getAlignment() {
                        return Alignment.ALIGN_CENTER;
                    }
                }, this.startTag, this.endTag, 33);
            }

            private void handleClickTag(String tag, Editable editable) {
                this.endTag = editable.length();
                this.text = editable.subSequence(this.startTag, this.endTag).toString();
                String[] tags = tag.split("click");
                if (tags.length == 2) {
                    this.flag = tags[1];
                }
                editable.setSpan(new ClickableSpan() {
                    public void onClick(View widget) {
                        HtmlTextView.this.mOnHtmlClickListener.onClick(C09261.this.flag, C09261.this.text);
                    }
                }, this.startTag, this.endTag, 33);
            }
        };
        if (VERSION.SDK_INT >= 24) {
            htmlSpan = Html.fromHtml(html, 63, null, handler);
        } else {
            htmlSpan = Html.fromHtml(html, null, handler);
        }
        setText(htmlSpan);
        setMovementMethod(BtLinkMovementMethod.getInstance());
    }
}
