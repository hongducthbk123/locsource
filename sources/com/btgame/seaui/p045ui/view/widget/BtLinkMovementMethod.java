package com.btgame.seaui.p045ui.view.widget;

import android.text.Layout;
import android.text.Selection;
import android.text.Spannable;
import android.text.method.LinkMovementMethod;
import android.text.method.MovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.view.MotionEvent;
import android.widget.TextView;

/* renamed from: com.btgame.seaui.ui.view.widget.BtLinkMovementMethod */
public class BtLinkMovementMethod extends LinkMovementMethod {
    private static LinkMovementMethod sInstance;

    public static MovementMethod getInstance() {
        if (sInstance == null) {
            sInstance = new BtLinkMovementMethod();
        }
        return sInstance;
    }

    public boolean onTouchEvent(TextView widget, Spannable buffer, MotionEvent event) {
        int action = event.getAction();
        if (action == 1 || action == 0 || action == 2) {
            int x = (((int) event.getX()) - widget.getTotalPaddingLeft()) + widget.getScrollX();
            int y = (((int) event.getY()) - widget.getTotalPaddingTop()) + widget.getScrollY();
            Layout layout = widget.getLayout();
            int off = layout.getOffsetForHorizontal(layout.getLineForVertical(y), (float) x);
            ClickableSpan[] link = (ClickableSpan[]) buffer.getSpans(off, off, ClickableSpan.class);
            if (link.length != 0) {
                if (action == 1) {
                    link[0].onClick(widget);
                    buffer.setSpan(new BackgroundColorSpan(0), buffer.getSpanStart(link[0]), buffer.getSpanEnd(link[0]), 33);
                    Selection.removeSelection(buffer);
                } else if (action == 0) {
                    buffer.setSpan(new BackgroundColorSpan(0), buffer.getSpanStart(link[0]), buffer.getSpanEnd(link[0]), 33);
                    Selection.setSelection(buffer, buffer.getSpanStart(link[0]), buffer.getSpanEnd(link[0]));
                } else if (action == 2) {
                    buffer.setSpan(new BackgroundColorSpan(0), buffer.getSpanStart(link[0]), buffer.getSpanEnd(link[0]), 33);
                    Selection.removeSelection(buffer);
                }
                return true;
            }
            Selection.removeSelection(buffer);
        }
        return false;
    }
}
