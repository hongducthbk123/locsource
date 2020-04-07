package com.sensorsdata.analytics.android.sdk.util;

import org.json.JSONException;
import org.json.JSONObject;

public class JSONUtils {
    public static String optionalStringKey(JSONObject o, String k) throws JSONException {
        if (!o.has(k) || o.isNull(k)) {
            return null;
        }
        return o.getString(k);
    }

    private static void addIndentBlank(StringBuilder sb, int indent) {
        int i = 0;
        while (i < indent) {
            try {
                sb.append(9);
                i++;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
    }

    public static String formatJson(String jsonStr) {
        if (jsonStr != null) {
            try {
                if (!"".equals(jsonStr)) {
                    StringBuilder sb = new StringBuilder();
                    char current = 0;
                    int indent = 0;
                    boolean isInQuotationMarks = false;
                    for (int i = 0; i < jsonStr.length(); i++) {
                        char last = current;
                        current = jsonStr.charAt(i);
                        switch (current) {
                            case '\"':
                                if (last != '\\') {
                                    isInQuotationMarks = !isInQuotationMarks;
                                }
                                sb.append(current);
                                break;
                            case ',':
                                sb.append(current);
                                if (last != '\\' && !isInQuotationMarks) {
                                    sb.append(10);
                                    addIndentBlank(sb, indent);
                                    break;
                                }
                            case '[':
                            case '{':
                                sb.append(current);
                                if (isInQuotationMarks) {
                                    break;
                                } else {
                                    sb.append(10);
                                    indent++;
                                    addIndentBlank(sb, indent);
                                    break;
                                }
                            case ']':
                            case '}':
                                if (!isInQuotationMarks) {
                                    sb.append(10);
                                    indent--;
                                    addIndentBlank(sb, indent);
                                }
                                sb.append(current);
                                break;
                            default:
                                sb.append(current);
                                break;
                        }
                    }
                    return sb.toString();
                }
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        }
        return "";
    }
}
