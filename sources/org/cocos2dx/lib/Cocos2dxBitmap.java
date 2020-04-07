package org.cocos2dx.lib;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.util.Log;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.LinkedList;
import org.apache.commons.p052io.IOUtils;

public class Cocos2dxBitmap {
    private static final int HORIZONTALALIGN_CENTER = 3;
    private static final int HORIZONTALALIGN_LEFT = 1;
    private static final int HORIZONTALALIGN_RIGHT = 2;
    private static final int VERTICALALIGN_BOTTOM = 2;
    private static final int VERTICALALIGN_CENTER = 3;
    private static final int VERTICALALIGN_TOP = 1;
    private static Context sContext;

    private static class TextProperty {
        /* access modifiers changed from: private */
        public final int mHeightPerLine;
        /* access modifiers changed from: private */
        public final String[] mLines;
        /* access modifiers changed from: private */
        public final int mMaxWidth;
        /* access modifiers changed from: private */
        public final int mTotalHeight;

        TextProperty(int maxWidth, int heightPerLine, String[] lines) {
            this.mMaxWidth = maxWidth;
            this.mHeightPerLine = heightPerLine;
            this.mTotalHeight = lines.length * heightPerLine;
            this.mLines = lines;
        }
    }

    private static native void nativeInitBitmapDC(int i, int i2, byte[] bArr);

    public static void setContext(Context context) {
        sContext = context;
    }

    public static void createTextBitmap(String string, String fontName, int fontSize, int alignment, int width, int height) {
        createTextBitmapShadowStroke(string.getBytes(), fontName, fontSize, 255, 255, 255, 255, alignment, width, height, false, 0.0f, 0.0f, 0.0f, 0.0f, false, 255, 255, 255, 255, 0.0f);
    }

    public static boolean createTextBitmapShadowStroke(byte[] array, String fontName, int fontSize, int fontTintR, int fontTintG, int fontTintB, int fontTintA, int alignment, int width, int height, boolean shadow, float shadowDX, float shadowDY, float shadowBlur, float shadowOpacity, boolean stroke, int strokeR, int strokeG, int strokeB, int strokeA, float strokeSize) {
        int bitmapTotalHeight;
        String str = new String(array);
        int horizontalAlignment = alignment & 15;
        int verticalAlignment = (alignment >> 4) & 15;
        String string = refactorString(str);
        Paint paint = newPaint(fontName, fontSize, horizontalAlignment);
        if (width == 0 || ((int) Math.ceil((double) paint.measureText(string, 0, 1))) <= width) {
            paint.setARGB(fontTintA, fontTintR, fontTintG, fontTintB);
            TextProperty textProperty = computeTextProperty(string, width, height, paint);
            if (height == 0) {
                bitmapTotalHeight = textProperty.mTotalHeight;
            } else {
                bitmapTotalHeight = height;
            }
            if (textProperty.mMaxWidth == 0 || bitmapTotalHeight == 0) {
                Log.w("createTextBitmapShadowStroke warning:", "textProperty MaxWidth is 0 or bitMapTotalHeight is 0\n");
                return false;
            }
            Bitmap bitmap = Bitmap.createBitmap(textProperty.mMaxWidth + ((int) 0.0f), ((int) 0.0f) + bitmapTotalHeight, Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            FontMetricsInt fontMetricsInt = paint.getFontMetricsInt();
            if (stroke) {
                Paint paintStroke = newPaint(fontName, fontSize, horizontalAlignment);
                paintStroke.setStyle(Style.STROKE);
                paintStroke.setStrokeWidth(strokeSize);
                paintStroke.setARGB(strokeA, strokeR, strokeG, strokeB);
                int y = computeY(fontMetricsInt, height, textProperty.mTotalHeight, verticalAlignment);
                String[] lines2 = textProperty.mLines;
                int length = lines2.length;
                for (int i = 0; i < length; i++) {
                    String line = lines2[i];
                    int x = computeX(line, textProperty.mMaxWidth, horizontalAlignment);
                    canvas.drawText(line, ((float) x) + 0.0f, ((float) y) + 0.0f, paintStroke);
                    canvas.drawText(line, ((float) x) + 0.0f, ((float) y) + 0.0f, paint);
                    y += textProperty.mHeightPerLine;
                }
            } else {
                int y2 = computeY(fontMetricsInt, height, textProperty.mTotalHeight, verticalAlignment);
                String[] lines = textProperty.mLines;
                int length2 = lines.length;
                for (int i2 = 0; i2 < length2; i2++) {
                    String line2 = lines[i2];
                    canvas.drawText(line2, ((float) computeX(line2, textProperty.mMaxWidth, horizontalAlignment)) + 0.0f, ((float) y2) + 0.0f, paint);
                    y2 += textProperty.mHeightPerLine;
                }
            }
            initNativeObject(bitmap);
            return true;
        }
        Log.w("createTextBitmapShadowStroke warning:", "the input width is less than the width of the pString's first word\n");
        return false;
    }

    private static Paint newPaint(String fontName, int fontSize, int horizontalAlignment) {
        Paint paint = new Paint();
        paint.setColor(-1);
        paint.setTextSize((float) fontSize);
        paint.setAntiAlias(true);
        if (fontName.endsWith(".ttf")) {
            try {
                paint.setTypeface(Cocos2dxTypefaces.get(sContext, fontName));
            } catch (Exception e) {
                Log.e("Cocos2dxBitmap", "error to create ttf type face: " + fontName);
                paint.setTypeface(Typeface.create(fontName, 0));
            }
        } else {
            paint.setTypeface(Typeface.create(fontName, 0));
        }
        switch (horizontalAlignment) {
            case 2:
                paint.setTextAlign(Align.RIGHT);
                break;
            case 3:
                paint.setTextAlign(Align.CENTER);
                break;
            default:
                paint.setTextAlign(Align.LEFT);
                break;
        }
        return paint;
    }

    private static TextProperty computeTextProperty(String string, int width, int height, Paint paint) {
        FontMetricsInt fm = paint.getFontMetricsInt();
        int h = (int) Math.ceil((double) (fm.bottom - fm.top));
        int maxContentWidth = 0;
        String[] lines = splitString(string, width, height, paint);
        if (width != 0) {
            maxContentWidth = width;
        } else {
            for (String line : lines) {
                int temp = (int) Math.ceil((double) paint.measureText(line, 0, line.length()));
                if (temp > maxContentWidth) {
                    maxContentWidth = temp;
                }
            }
        }
        return new TextProperty(maxContentWidth, h, lines);
    }

    private static int computeX(String text, int maxWidth, int horizontalAlignment) {
        switch (horizontalAlignment) {
            case 2:
                return maxWidth;
            case 3:
                return maxWidth / 2;
            default:
                return 0;
        }
    }

    private static int computeY(FontMetricsInt fontMetricsInt, int constrainHeight, int totalHeight, int verticalAlignment) {
        int y = -fontMetricsInt.top;
        if (constrainHeight <= totalHeight) {
            return y;
        }
        switch (verticalAlignment) {
            case 1:
                return -fontMetricsInt.top;
            case 2:
                return (-fontMetricsInt.top) + (constrainHeight - totalHeight);
            case 3:
                return (-fontMetricsInt.top) + ((constrainHeight - totalHeight) / 2);
            default:
                return y;
        }
    }

    private static String[] splitString(String string, int maxWidth, int maxHeight, Paint paint) {
        String[] lines = string.split("\\n");
        FontMetricsInt fm = paint.getFontMetricsInt();
        int maxLines = maxHeight / ((int) Math.ceil((double) (fm.bottom - fm.top)));
        if (maxWidth != 0) {
            LinkedList<String> strList = new LinkedList<>();
            for (String line : lines) {
                if (((int) Math.ceil((double) paint.measureText(line))) > maxWidth) {
                    strList.addAll(divideStringWithMaxWidth(line, maxWidth, paint));
                } else {
                    strList.add(line);
                }
                if (maxLines > 0 && strList.size() >= maxLines) {
                    break;
                }
            }
            if (maxLines > 0 && strList.size() > maxLines) {
                while (strList.size() > maxLines) {
                    strList.removeLast();
                }
            }
            String[] ret = new String[strList.size()];
            strList.toArray(ret);
            return ret;
        } else if (maxHeight == 0 || lines.length <= maxLines) {
            return lines;
        } else {
            LinkedList<String> strList2 = new LinkedList<>();
            for (int i = 0; i < maxLines; i++) {
                strList2.add(lines[i]);
            }
            String[] ret2 = new String[strList2.size()];
            strList2.toArray(ret2);
            return ret2;
        }
    }

    private static LinkedList<String> divideStringWithMaxWidth(String string, int maxWidth, Paint paint) {
        int charLength = string.length();
        int start = 0;
        LinkedList<String> strList = new LinkedList<>();
        int i = 1;
        while (i <= charLength) {
            int tempWidth = (int) Math.ceil((double) paint.measureText(string, start, i));
            if (tempWidth >= maxWidth) {
                int lastIndexOfSpace = string.substring(0, i).lastIndexOf(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                if (lastIndexOfSpace != -1 && lastIndexOfSpace > start) {
                    strList.add(string.substring(start, lastIndexOfSpace));
                    i = lastIndexOfSpace + 1;
                } else if (tempWidth <= maxWidth || i == start + 1) {
                    strList.add(string.substring(start, i));
                } else {
                    strList.add(string.substring(start, i - 1));
                    i--;
                }
                while (i < charLength && string.charAt(i) == ' ') {
                    i++;
                }
                start = i;
            }
            i++;
        }
        if (start < charLength) {
            strList.add(string.substring(start));
        }
        return strList;
    }

    private static String refactorString(String string) {
        if (string.compareTo("") == 0) {
            return MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR;
        }
        StringBuilder strBuilder = new StringBuilder(string);
        int start = 0;
        for (int index = strBuilder.indexOf(IOUtils.LINE_SEPARATOR_UNIX); index != -1; index = strBuilder.indexOf(IOUtils.LINE_SEPARATOR_UNIX, start)) {
            if (index == 0 || strBuilder.charAt(index - 1) == 10) {
                strBuilder.insert(start, MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                start = index + 2;
            } else {
                start = index + 1;
            }
            if (start > strBuilder.length() || index == strBuilder.length()) {
                break;
            }
        }
        return strBuilder.toString();
    }

    private static void initNativeObject(Bitmap bitmap) {
        byte[] pixels = getPixels(bitmap);
        if (pixels != null) {
            nativeInitBitmapDC(bitmap.getWidth(), bitmap.getHeight(), pixels);
        }
    }

    private static byte[] getPixels(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        byte[] pixels = new byte[(bitmap.getWidth() * bitmap.getHeight() * 4)];
        ByteBuffer buf = ByteBuffer.wrap(pixels);
        buf.order(ByteOrder.nativeOrder());
        bitmap.copyPixelsToBuffer(buf);
        return pixels;
    }

    public static int getFontSizeAccordingHeight(int height) {
        Paint paint = new Paint();
        Rect bounds = new Rect();
        paint.setTypeface(Typeface.DEFAULT);
        int incr_text_size = 1;
        boolean found_desired_size = false;
        while (!found_desired_size) {
            paint.setTextSize((float) incr_text_size);
            String text = "SghMNy";
            paint.getTextBounds(text, 0, text.length(), bounds);
            incr_text_size++;
            if (height - bounds.height() <= 2) {
                found_desired_size = true;
            }
            Log.d("font size", "incr size:" + incr_text_size);
        }
        return incr_text_size;
    }

    private static String getStringWithEllipsis(String string, float width, float fontSize) {
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        TextPaint paint = new TextPaint();
        paint.setTypeface(Typeface.DEFAULT);
        paint.setTextSize(fontSize);
        return TextUtils.ellipsize(string, paint, width, TruncateAt.END).toString();
    }
}
