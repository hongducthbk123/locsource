package com.google.api.client.googleapis.auth.clientlogin;

import com.google.api.client.http.HttpResponse;
import com.google.api.client.util.Beta;
import com.google.api.client.util.ClassInfo;
import com.google.api.client.util.FieldInfo;
import com.google.api.client.util.GenericData;
import com.google.api.client.util.ObjectParser;
import com.google.api.client.util.Types;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.Map;
import org.apache.http.protocol.HTTP;

@Beta
final class AuthKeyValueParser implements ObjectParser {
    public static final AuthKeyValueParser INSTANCE = new AuthKeyValueParser();

    public String getContentType() {
        return HTTP.PLAIN_TEXT_TYPE;
    }

    public <T> T parse(HttpResponse response, Class<T> dataClass) throws IOException {
        response.setContentLoggingLimit(0);
        InputStream content = response.getContent();
        try {
            return parse(content, dataClass);
        } finally {
            content.close();
        }
    }

    public <T> T parse(InputStream content, Class<T> dataClass) throws IOException {
        Object obj;
        ClassInfo classInfo = ClassInfo.m1470of(dataClass);
        T newInstance = Types.newInstance(dataClass);
        BufferedReader reader = new BufferedReader(new InputStreamReader(content));
        while (true) {
            String line = reader.readLine();
            if (line == null) {
                return newInstance;
            }
            int equals = line.indexOf(61);
            String key = line.substring(0, equals);
            String value = line.substring(equals + 1);
            Field field = classInfo.getField(key);
            if (field != null) {
                Class<?> fieldClass = field.getType();
                if (fieldClass == Boolean.TYPE || fieldClass == Boolean.class) {
                    obj = Boolean.valueOf(value);
                } else {
                    obj = value;
                }
                FieldInfo.setFieldValue(field, newInstance, obj);
            } else if (GenericData.class.isAssignableFrom(dataClass)) {
                ((GenericData) newInstance).set(key, value);
            } else if (Map.class.isAssignableFrom(dataClass)) {
                ((Map) newInstance).put(key, value);
            }
        }
    }

    private AuthKeyValueParser() {
    }

    public <T> T parseAndClose(InputStream in, Charset charset, Class<T> dataClass) throws IOException {
        return parseAndClose(new InputStreamReader(in, charset), dataClass);
    }

    public Object parseAndClose(InputStream in, Charset charset, Type dataType) {
        throw new UnsupportedOperationException("Type-based parsing is not yet supported -- use Class<T> instead");
    }

    public <T> T parseAndClose(Reader reader, Class<T> dataClass) throws IOException {
        Object obj;
        try {
            ClassInfo classInfo = ClassInfo.m1470of(dataClass);
            T newInstance = Types.newInstance(dataClass);
            BufferedReader breader = new BufferedReader(reader);
            while (true) {
                String line = breader.readLine();
                if (line == null) {
                    return newInstance;
                }
                int equals = line.indexOf(61);
                String key = line.substring(0, equals);
                String value = line.substring(equals + 1);
                Field field = classInfo.getField(key);
                if (field != null) {
                    Class<?> fieldClass = field.getType();
                    if (fieldClass == Boolean.TYPE || fieldClass == Boolean.class) {
                        obj = Boolean.valueOf(value);
                    } else {
                        obj = value;
                    }
                    FieldInfo.setFieldValue(field, newInstance, obj);
                } else if (GenericData.class.isAssignableFrom(dataClass)) {
                    ((GenericData) newInstance).set(key, value);
                } else if (Map.class.isAssignableFrom(dataClass)) {
                    ((Map) newInstance).put(key, value);
                }
            }
        } finally {
            reader.close();
        }
    }

    public Object parseAndClose(Reader reader, Type dataType) {
        throw new UnsupportedOperationException("Type-based parsing is not yet supported -- use Class<T> instead");
    }
}
