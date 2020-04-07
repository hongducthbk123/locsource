package p004cn.jpush.android.p041e.p042a;

import android.text.TextUtils;
import android.webkit.WebView;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: cn.jpush.android.e.a.d */
public final class C0599d {

    /* renamed from: a */
    private HashMap<String, Method> f868a;

    /* renamed from: b */
    private String f869b;

    /* renamed from: c */
    private String f870c;

    public C0599d(String str, Class cls) {
        try {
            if (TextUtils.isEmpty(str)) {
                throw new Exception("injected name can not be null");
            }
            this.f869b = str;
            this.f868a = new HashMap<>();
            Method[] declaredMethods = cls.getDeclaredMethods();
            StringBuilder sb = new StringBuilder("javascript:(function(b){console.log(\"");
            sb.append(this.f869b);
            sb.append(" initialization begin\");var a={queue:[],callback:function(){var d=Array.prototype.slice.call(arguments,0);var c=d.shift();var e=d.shift();this.queue[c].apply(this,d);if(!e){delete this.queue[c]}}};");
            for (Method method : declaredMethods) {
                if (method.getModifiers() == 9) {
                    String a = m1356a(method);
                    if (a != null) {
                        this.f868a.put(a, method);
                        sb.append(String.format(Locale.ENGLISH, "a.%s=", new Object[]{method.getName()}));
                    }
                }
            }
            sb.append("function(){var f=Array.prototype.slice.call(arguments,0);if(f.length<1){throw\"");
            sb.append(this.f869b);
            sb.append(" call error, message:miss method name\"}var e=[];for(var h=1;h<f.length;h++){var c=f[h];var j=typeof c;e[e.length]=j;if(j==\"function\"){var d=a.queue.length;a.queue[d]=c;f[h]=d}}var g=JSON.parse(prompt(JSON.stringify({method:f.shift(),types:e,args:f})));if(g.code!=200){throw\"");
            sb.append(this.f869b);
            sb.append(" call error, code:\"+g.code+\", message:\"+g.result}return g.result};Object.getOwnPropertyNames(a).forEach(function(d){var c=a[d];if(typeof c===\"function\"&&d!==\"callback\"){a[d]=function(){return c.apply(a,[d].concat(Array.prototype.slice.call(arguments,0)))}}});b.");
            sb.append(this.f869b);
            sb.append("=a;console.log(\"");
            sb.append(this.f869b);
            sb.append(" initialization end\")})(window);");
            this.f870c = sb.toString();
        } catch (Exception e) {
        }
    }

    /* renamed from: a */
    private static String m1356a(Method method) {
        String name = method.getName();
        Class<JSONObject>[] parameterTypes = method.getParameterTypes();
        int length = parameterTypes.length;
        if (length <= 0 || parameterTypes[0] != WebView.class) {
            return null;
        }
        String str = name;
        for (int i = 1; i < length; i++) {
            Class<JSONObject> cls = parameterTypes[i];
            if (cls == String.class) {
                str = str + "_S";
            } else if (cls == Integer.TYPE || cls == Long.TYPE || cls == Float.TYPE || cls == Double.TYPE) {
                str = str + "_N";
            } else if (cls == Boolean.TYPE) {
                str = str + "_B";
            } else if (cls == JSONObject.class) {
                str = str + "_O";
            } else {
                str = str + "_P";
            }
        }
        return str;
    }

    /* renamed from: a */
    public final String mo6914a() {
        return this.f870c;
    }

    /* renamed from: a */
    public final String mo6915a(WebView webView, String str) {
        String str2;
        int i;
        if (TextUtils.isEmpty(str)) {
            return m1355a(500, (Object) "call data empty");
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString("method");
            JSONArray jSONArray = jSONObject.getJSONArray("types");
            JSONArray jSONArray2 = jSONObject.getJSONArray("args");
            int length = jSONArray.length();
            Object[] objArr = new Object[(length + 1)];
            objArr[0] = webView;
            int i2 = 0;
            String str3 = string;
            int i3 = 0;
            while (i2 < length) {
                String optString = jSONArray.optString(i2);
                if ("string".equals(optString)) {
                    String str4 = str3 + "_S";
                    objArr[i2 + 1] = jSONArray2.isNull(i2) ? null : jSONArray2.getString(i2);
                    int i4 = i3;
                    str2 = str4;
                    i = i4;
                } else if ("number".equals(optString)) {
                    String str5 = str3 + "_N";
                    i = (i3 * 10) + i2 + 1;
                    str2 = str5;
                } else if ("boolean".equals(optString)) {
                    String str6 = str3 + "_B";
                    objArr[i2 + 1] = Boolean.valueOf(jSONArray2.getBoolean(i2));
                    int i5 = i3;
                    str2 = str6;
                    i = i5;
                } else if ("object".equals(optString)) {
                    String str7 = str3 + "_O";
                    objArr[i2 + 1] = jSONArray2.isNull(i2) ? null : jSONArray2.getJSONObject(i2);
                    int i6 = i3;
                    str2 = str7;
                    i = i6;
                } else {
                    int i7 = i3;
                    str2 = str3 + "_P";
                    i = i7;
                }
                i2++;
                str3 = str2;
                i3 = i;
            }
            Method method = (Method) this.f868a.get(str3);
            if (method == null) {
                return m1355a(500, (Object) "not found method(" + str3 + ") with valid parameters");
            }
            if (i3 > 0) {
                Class[] parameterTypes = method.getParameterTypes();
                while (i3 > 0) {
                    int i8 = i3 - ((i3 / 10) * 10);
                    Class cls = parameterTypes[i8];
                    if (cls == Integer.TYPE) {
                        objArr[i8] = Integer.valueOf(jSONArray2.getInt(i8 - 1));
                    } else if (cls == Long.TYPE) {
                        objArr[i8] = Long.valueOf(Long.parseLong(jSONArray2.getString(i8 - 1)));
                    } else {
                        objArr[i8] = Double.valueOf(jSONArray2.getDouble(i8 - 1));
                    }
                    i3 /= 10;
                }
            }
            return m1355a(200, method.invoke(null, objArr));
        } catch (Exception e) {
            if (e.getCause() != null) {
                return m1355a(500, (Object) "method execute error:" + e.getCause().getMessage());
            }
            return m1355a(500, (Object) "method execute error:" + e.getMessage());
        }
    }

    /* renamed from: a */
    private static String m1355a(int i, Object obj) {
        String str = "";
        if (obj == null) {
            str = "null";
        } else if (obj instanceof String) {
            str = "\"" + ((String) obj).replace("\"", "\\\"") + "\"";
        } else if ((obj instanceof Integer) || (obj instanceof Long) || (obj instanceof Boolean) || (obj instanceof Float) || (obj instanceof Double) || (obj instanceof JSONObject)) {
            str = String.valueOf(obj);
        }
        return String.format(Locale.ENGLISH, "{\"code\": %d, \"result\": %s}", new Object[]{Integer.valueOf(i), str});
    }
}
