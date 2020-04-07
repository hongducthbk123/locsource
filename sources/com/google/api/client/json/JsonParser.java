package com.google.api.client.json;

import com.google.api.client.json.JsonPolymorphicTypeMap.TypeDef;
import com.google.api.client.util.Beta;
import com.google.api.client.util.ClassInfo;
import com.google.api.client.util.Data;
import com.google.api.client.util.FieldInfo;
import com.google.api.client.util.GenericData;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.Sets;
import com.google.api.client.util.Types;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public abstract class JsonParser {
    private static WeakHashMap<Class<?>, Field> cachedTypemapFields = new WeakHashMap<>();
    private static final Lock lock = new ReentrantLock();

    public abstract void close() throws IOException;

    public abstract BigInteger getBigIntegerValue() throws IOException;

    public abstract byte getByteValue() throws IOException;

    public abstract String getCurrentName() throws IOException;

    public abstract JsonToken getCurrentToken();

    public abstract BigDecimal getDecimalValue() throws IOException;

    public abstract double getDoubleValue() throws IOException;

    public abstract JsonFactory getFactory();

    public abstract float getFloatValue() throws IOException;

    public abstract int getIntValue() throws IOException;

    public abstract long getLongValue() throws IOException;

    public abstract short getShortValue() throws IOException;

    public abstract String getText() throws IOException;

    public abstract JsonToken nextToken() throws IOException;

    public abstract JsonParser skipChildren() throws IOException;

    public final <T> T parseAndClose(Class<T> destinationClass) throws IOException {
        return parseAndClose(destinationClass, (CustomizeJsonParser) null);
    }

    @Beta
    public final <T> T parseAndClose(Class<T> destinationClass, CustomizeJsonParser customizeParser) throws IOException {
        try {
            return parse(destinationClass, customizeParser);
        } finally {
            close();
        }
    }

    public final void skipToKey(String keyToFind) throws IOException {
        skipToKey(Collections.singleton(keyToFind));
    }

    public final String skipToKey(Set<String> keysToFind) throws IOException {
        JsonToken curToken = startParsingObjectOrArray();
        while (curToken == JsonToken.FIELD_NAME) {
            String key = getText();
            nextToken();
            if (keysToFind.contains(key)) {
                return key;
            }
            skipChildren();
            curToken = nextToken();
        }
        return null;
    }

    private JsonToken startParsing() throws IOException {
        JsonToken currentToken = getCurrentToken();
        if (currentToken == null) {
            currentToken = nextToken();
        }
        Preconditions.checkArgument(currentToken != null, "no JSON input found");
        return currentToken;
    }

    private JsonToken startParsingObjectOrArray() throws IOException {
        JsonToken currentToken = startParsing();
        switch (currentToken) {
            case START_OBJECT:
                JsonToken currentToken2 = nextToken();
                Preconditions.checkArgument(currentToken2 == JsonToken.FIELD_NAME || currentToken2 == JsonToken.END_OBJECT, currentToken2);
                return currentToken2;
            case START_ARRAY:
                return nextToken();
            default:
                return currentToken;
        }
    }

    public final void parseAndClose(Object destination) throws IOException {
        parseAndClose(destination, (CustomizeJsonParser) null);
    }

    @Beta
    public final void parseAndClose(Object destination, CustomizeJsonParser customizeParser) throws IOException {
        try {
            parse(destination, customizeParser);
        } finally {
            close();
        }
    }

    public final <T> T parse(Class<T> destinationClass) throws IOException {
        return parse(destinationClass, (CustomizeJsonParser) null);
    }

    @Beta
    public final <T> T parse(Class<T> destinationClass, CustomizeJsonParser customizeParser) throws IOException {
        return parse((Type) destinationClass, false, customizeParser);
    }

    public Object parse(Type dataType, boolean close) throws IOException {
        return parse(dataType, close, (CustomizeJsonParser) null);
    }

    @Beta
    public Object parse(Type dataType, boolean close, CustomizeJsonParser customizeParser) throws IOException {
        try {
            if (!Void.class.equals(dataType)) {
                startParsing();
            }
            return parseValue(null, dataType, new ArrayList(), null, customizeParser, true);
        } finally {
            if (close) {
                close();
            }
        }
    }

    public final void parse(Object destination) throws IOException {
        parse(destination, (CustomizeJsonParser) null);
    }

    @Beta
    public final void parse(Object destination, CustomizeJsonParser customizeParser) throws IOException {
        ArrayList<Type> context = new ArrayList<>();
        context.add(destination.getClass());
        parse(context, destination, customizeParser);
    }

    private void parse(ArrayList<Type> context, Object destination, CustomizeJsonParser customizeParser) throws IOException {
        if (destination instanceof GenericJson) {
            ((GenericJson) destination).setFactory(getFactory());
        }
        JsonToken curToken = startParsingObjectOrArray();
        Class<?> destinationClass = destination.getClass();
        ClassInfo classInfo = ClassInfo.m1470of(destinationClass);
        boolean isGenericData = GenericData.class.isAssignableFrom(destinationClass);
        if (isGenericData || !Map.class.isAssignableFrom(destinationClass)) {
            while (curToken == JsonToken.FIELD_NAME) {
                String key = getText();
                nextToken();
                if (customizeParser == null || !customizeParser.stopAt(destination, key)) {
                    FieldInfo fieldInfo = classInfo.getFieldInfo(key);
                    if (fieldInfo != null) {
                        if (!fieldInfo.isFinal() || fieldInfo.isPrimitive()) {
                            Field field = fieldInfo.getField();
                            int contextSize = context.size();
                            context.add(field.getGenericType());
                            Object fieldValue = parseValue(field, fieldInfo.getGenericType(), context, destination, customizeParser, true);
                            context.remove(contextSize);
                            fieldInfo.setValue(destination, fieldValue);
                        } else {
                            throw new IllegalArgumentException("final array/object fields are not supported");
                        }
                    } else if (isGenericData) {
                        ((GenericData) destination).set(key, parseValue(null, null, context, destination, customizeParser, true));
                    } else {
                        if (customizeParser != null) {
                            customizeParser.handleUnrecognizedKey(destination, key);
                        }
                        skipChildren();
                    }
                    curToken = nextToken();
                } else {
                    return;
                }
            }
            return;
        }
        parseMap(null, (Map) destination, Types.getMapValueParameter(destinationClass), context, customizeParser);
    }

    public final <T> Collection<T> parseArrayAndClose(Class<?> destinationCollectionClass, Class<T> destinationItemClass) throws IOException {
        return parseArrayAndClose(destinationCollectionClass, destinationItemClass, (CustomizeJsonParser) null);
    }

    @Beta
    public final <T> Collection<T> parseArrayAndClose(Class<?> destinationCollectionClass, Class<T> destinationItemClass, CustomizeJsonParser customizeParser) throws IOException {
        try {
            return parseArray(destinationCollectionClass, destinationItemClass, customizeParser);
        } finally {
            close();
        }
    }

    public final <T> void parseArrayAndClose(Collection<? super T> destinationCollection, Class<T> destinationItemClass) throws IOException {
        parseArrayAndClose(destinationCollection, destinationItemClass, (CustomizeJsonParser) null);
    }

    @Beta
    public final <T> void parseArrayAndClose(Collection<? super T> destinationCollection, Class<T> destinationItemClass, CustomizeJsonParser customizeParser) throws IOException {
        try {
            parseArray(destinationCollection, destinationItemClass, customizeParser);
        } finally {
            close();
        }
    }

    public final <T> Collection<T> parseArray(Class<?> destinationCollectionClass, Class<T> destinationItemClass) throws IOException {
        return parseArray(destinationCollectionClass, destinationItemClass, (CustomizeJsonParser) null);
    }

    @Beta
    public final <T> Collection<T> parseArray(Class<?> destinationCollectionClass, Class<T> destinationItemClass, CustomizeJsonParser customizeParser) throws IOException {
        Collection<T> destinationCollection = Data.newCollectionInstance(destinationCollectionClass);
        parseArray(destinationCollection, destinationItemClass, customizeParser);
        return destinationCollection;
    }

    public final <T> void parseArray(Collection<? super T> destinationCollection, Class<T> destinationItemClass) throws IOException {
        parseArray(destinationCollection, destinationItemClass, (CustomizeJsonParser) null);
    }

    @Beta
    public final <T> void parseArray(Collection<? super T> destinationCollection, Class<T> destinationItemClass, CustomizeJsonParser customizeParser) throws IOException {
        parseArray(null, destinationCollection, destinationItemClass, new ArrayList(), customizeParser);
    }

    private <T> void parseArray(Field fieldContext, Collection<T> destinationCollection, Type destinationItemType, ArrayList<Type> context, CustomizeJsonParser customizeParser) throws IOException {
        JsonToken curToken = startParsingObjectOrArray();
        while (curToken != JsonToken.END_ARRAY) {
            destinationCollection.add(parseValue(fieldContext, destinationItemType, context, destinationCollection, customizeParser, true));
            curToken = nextToken();
        }
    }

    private void parseMap(Field fieldContext, Map<String, Object> destinationMap, Type valueType, ArrayList<Type> context, CustomizeJsonParser customizeParser) throws IOException {
        JsonToken curToken = startParsingObjectOrArray();
        while (curToken == JsonToken.FIELD_NAME) {
            String key = getText();
            nextToken();
            if (customizeParser == null || !customizeParser.stopAt(destinationMap, key)) {
                destinationMap.put(key, parseValue(fieldContext, valueType, context, destinationMap, customizeParser, true));
                curToken = nextToken();
            } else {
                return;
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:112:0x0255, code lost:
        if (r37.isAssignableFrom(java.lang.Boolean.class) != false) goto L_0x0257;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:177:0x0386, code lost:
        if (r30.equals("-infinity") == false) goto L_0x0388;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:183:0x039e, code lost:
        if (r39.getAnnotation(com.google.api.client.json.JsonString.class) != null) goto L_0x03a0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00ae, code lost:
        if (com.google.api.client.util.Types.isAssignableToOrFrom(r37, java.util.Collection.class) != false) goto L_0x00b0;
     */
    /* JADX WARNING: Removed duplicated region for block: B:116:0x0269 A[Catch:{ IllegalArgumentException -> 0x005f }] */
    /* JADX WARNING: Removed duplicated region for block: B:118:0x026f A[Catch:{ IllegalArgumentException -> 0x005f }] */
    /* JADX WARNING: Removed duplicated region for block: B:125:0x0287 A[Catch:{ IllegalArgumentException -> 0x005f }] */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x006b  */
    /* JADX WARNING: Removed duplicated region for block: B:211:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x007a  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00cd A[Catch:{ IllegalArgumentException -> 0x005f }] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00d4 A[Catch:{ IllegalArgumentException -> 0x005f }] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00eb A[Catch:{ IllegalArgumentException -> 0x005f }] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00f9 A[ADDED_TO_REGION, Catch:{ IllegalArgumentException -> 0x005f }] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x010a A[Catch:{ IllegalArgumentException -> 0x005f }] */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x0145 A[Catch:{ IllegalArgumentException -> 0x005f }] */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x0152 A[Catch:{ IllegalArgumentException -> 0x005f }] */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x016f A[Catch:{ IllegalArgumentException -> 0x005f }] */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0175 A[Catch:{ IllegalArgumentException -> 0x005f }] */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x018e A[Catch:{ IllegalArgumentException -> 0x005f }] */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x01a2 A[Catch:{ IllegalArgumentException -> 0x005f }] */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x01af A[Catch:{ IllegalArgumentException -> 0x005f }] */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x01b8 A[Catch:{ IllegalArgumentException -> 0x005f }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final java.lang.Object parseValue(java.lang.reflect.Field r39, java.lang.reflect.Type r40, java.util.ArrayList<java.lang.reflect.Type> r41, java.lang.Object r42, com.google.api.client.json.CustomizeJsonParser r43, boolean r44) throws java.io.IOException {
        /*
            r38 = this;
            r0 = r41
            r1 = r40
            java.lang.reflect.Type r40 = com.google.api.client.util.Data.resolveWildcardTypeOrTypeVariable(r0, r1)
            r0 = r40
            boolean r4 = r0 instanceof java.lang.Class
            if (r4 == 0) goto L_0x002d
            r4 = r40
            java.lang.Class r4 = (java.lang.Class) r4
            r37 = r4
        L_0x0014:
            r0 = r40
            boolean r4 = r0 instanceof java.lang.reflect.ParameterizedType
            if (r4 == 0) goto L_0x0022
            r4 = r40
            java.lang.reflect.ParameterizedType r4 = (java.lang.reflect.ParameterizedType) r4
            java.lang.Class r37 = com.google.api.client.util.Types.getRawClass(r4)
        L_0x0022:
            java.lang.Class<java.lang.Void> r4 = java.lang.Void.class
            r0 = r37
            if (r0 != r4) goto L_0x0030
            r38.skipChildren()
            r4 = 0
        L_0x002c:
            return r4
        L_0x002d:
            r37 = 0
            goto L_0x0014
        L_0x0030:
            com.google.api.client.json.JsonToken r31 = r38.getCurrentToken()
            int[] r4 = com.google.api.client.json.JsonParser.C12421.$SwitchMap$com$google$api$client$json$JsonToken     // Catch:{ IllegalArgumentException -> 0x005f }
            com.google.api.client.json.JsonToken r5 = r38.getCurrentToken()     // Catch:{ IllegalArgumentException -> 0x005f }
            int r5 = r5.ordinal()     // Catch:{ IllegalArgumentException -> 0x005f }
            r4 = r4[r5]     // Catch:{ IllegalArgumentException -> 0x005f }
            switch(r4) {
                case 1: goto L_0x010d;
                case 2: goto L_0x009c;
                case 3: goto L_0x009c;
                case 4: goto L_0x010d;
                case 5: goto L_0x010d;
                case 6: goto L_0x0243;
                case 7: goto L_0x0243;
                case 8: goto L_0x0273;
                case 9: goto L_0x0273;
                case 10: goto L_0x0344;
                case 11: goto L_0x03b4;
                default: goto L_0x0043;
            }     // Catch:{ IllegalArgumentException -> 0x005f }
        L_0x0043:
            java.lang.IllegalArgumentException r4 = new java.lang.IllegalArgumentException     // Catch:{ IllegalArgumentException -> 0x005f }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ IllegalArgumentException -> 0x005f }
            r5.<init>()     // Catch:{ IllegalArgumentException -> 0x005f }
            java.lang.String r8 = "unexpected JSON node type: "
            java.lang.StringBuilder r5 = r5.append(r8)     // Catch:{ IllegalArgumentException -> 0x005f }
            r0 = r31
            java.lang.StringBuilder r5 = r5.append(r0)     // Catch:{ IllegalArgumentException -> 0x005f }
            java.lang.String r5 = r5.toString()     // Catch:{ IllegalArgumentException -> 0x005f }
            r4.<init>(r5)     // Catch:{ IllegalArgumentException -> 0x005f }
            throw r4     // Catch:{ IllegalArgumentException -> 0x005f }
        L_0x005f:
            r23 = move-exception
            java.lang.StringBuilder r21 = new java.lang.StringBuilder
            r21.<init>()
            java.lang.String r22 = r38.getCurrentName()
            if (r22 == 0) goto L_0x0078
            java.lang.String r4 = "key "
            r0 = r21
            java.lang.StringBuilder r4 = r0.append(r4)
            r0 = r22
            r4.append(r0)
        L_0x0078:
            if (r39 == 0) goto L_0x0090
            if (r22 == 0) goto L_0x0083
            java.lang.String r4 = ", "
            r0 = r21
            r0.append(r4)
        L_0x0083:
            java.lang.String r4 = "field "
            r0 = r21
            java.lang.StringBuilder r4 = r0.append(r4)
            r0 = r39
            r4.append(r0)
        L_0x0090:
            java.lang.IllegalArgumentException r4 = new java.lang.IllegalArgumentException
            java.lang.String r5 = r21.toString()
            r0 = r23
            r4.<init>(r5, r0)
            throw r4
        L_0x009c:
            boolean r26 = com.google.api.client.util.Types.isArray(r40)     // Catch:{ IllegalArgumentException -> 0x005f }
            if (r40 == 0) goto L_0x00b0
            if (r26 != 0) goto L_0x00b0
            if (r37 == 0) goto L_0x00f7
            java.lang.Class<java.util.Collection> r4 = java.util.Collection.class
            r0 = r37
            boolean r4 = com.google.api.client.util.Types.isAssignableToOrFrom(r0, r4)     // Catch:{ IllegalArgumentException -> 0x005f }
            if (r4 == 0) goto L_0x00f7
        L_0x00b0:
            r4 = 1
        L_0x00b1:
            java.lang.String r5 = "expected collection or array type but got %s"
            r8 = 1
            java.lang.Object[] r8 = new java.lang.Object[r8]     // Catch:{ IllegalArgumentException -> 0x005f }
            r9 = 0
            r8[r9] = r40     // Catch:{ IllegalArgumentException -> 0x005f }
            com.google.api.client.util.Preconditions.checkArgument(r4, r5, r8)     // Catch:{ IllegalArgumentException -> 0x005f }
            r6 = 0
            if (r43 == 0) goto L_0x00cb
            if (r39 == 0) goto L_0x00cb
            r0 = r43
            r1 = r42
            r2 = r39
            java.util.Collection r6 = r0.newInstanceForArray(r1, r2)     // Catch:{ IllegalArgumentException -> 0x005f }
        L_0x00cb:
            if (r6 != 0) goto L_0x00d1
            java.util.Collection r6 = com.google.api.client.util.Data.newCollectionInstance(r40)     // Catch:{ IllegalArgumentException -> 0x005f }
        L_0x00d1:
            r7 = 0
            if (r26 == 0) goto L_0x00f9
            java.lang.reflect.Type r7 = com.google.api.client.util.Types.getArrayComponentType(r40)     // Catch:{ IllegalArgumentException -> 0x005f }
        L_0x00d8:
            r0 = r41
            java.lang.reflect.Type r7 = com.google.api.client.util.Data.resolveWildcardTypeOrTypeVariable(r0, r7)     // Catch:{ IllegalArgumentException -> 0x005f }
            r4 = r38
            r5 = r39
            r8 = r41
            r9 = r43
            r4.parseArray(r5, r6, r7, r8, r9)     // Catch:{ IllegalArgumentException -> 0x005f }
            if (r26 == 0) goto L_0x010a
            r0 = r41
            java.lang.Class r4 = com.google.api.client.util.Types.getRawArrayComponentType(r0, r7)     // Catch:{ IllegalArgumentException -> 0x005f }
            java.lang.Object r4 = com.google.api.client.util.Types.toArray(r6, r4)     // Catch:{ IllegalArgumentException -> 0x005f }
            goto L_0x002c
        L_0x00f7:
            r4 = 0
            goto L_0x00b1
        L_0x00f9:
            if (r37 == 0) goto L_0x00d8
            java.lang.Class<java.lang.Iterable> r4 = java.lang.Iterable.class
            r0 = r37
            boolean r4 = r4.isAssignableFrom(r0)     // Catch:{ IllegalArgumentException -> 0x005f }
            if (r4 == 0) goto L_0x00d8
            java.lang.reflect.Type r7 = com.google.api.client.util.Types.getIterableParameter(r40)     // Catch:{ IllegalArgumentException -> 0x005f }
            goto L_0x00d8
        L_0x010a:
            r4 = r6
            goto L_0x002c
        L_0x010d:
            boolean r4 = com.google.api.client.util.Types.isArray(r40)     // Catch:{ IllegalArgumentException -> 0x005f }
            if (r4 != 0) goto L_0x0186
            r4 = 1
        L_0x0114:
            java.lang.String r5 = "expected object or map type but got %s"
            r8 = 1
            java.lang.Object[] r8 = new java.lang.Object[r8]     // Catch:{ IllegalArgumentException -> 0x005f }
            r9 = 0
            r8[r9] = r40     // Catch:{ IllegalArgumentException -> 0x005f }
            com.google.api.client.util.Preconditions.checkArgument(r4, r5, r8)     // Catch:{ IllegalArgumentException -> 0x005f }
            if (r44 == 0) goto L_0x0188
            java.lang.reflect.Field r36 = getCachedTypemapFieldFor(r37)     // Catch:{ IllegalArgumentException -> 0x005f }
        L_0x0125:
            r29 = 0
            if (r37 == 0) goto L_0x0135
            if (r43 == 0) goto L_0x0135
            r0 = r43
            r1 = r42
            r2 = r37
            java.lang.Object r29 = r0.newInstanceForObject(r1, r2)     // Catch:{ IllegalArgumentException -> 0x005f }
        L_0x0135:
            if (r37 == 0) goto L_0x018b
            java.lang.Class<java.util.Map> r4 = java.util.Map.class
            r0 = r37
            boolean r4 = com.google.api.client.util.Types.isAssignableToOrFrom(r0, r4)     // Catch:{ IllegalArgumentException -> 0x005f }
            if (r4 == 0) goto L_0x018b
            r27 = 1
        L_0x0143:
            if (r36 == 0) goto L_0x018e
            com.google.api.client.json.GenericJson r29 = new com.google.api.client.json.GenericJson     // Catch:{ IllegalArgumentException -> 0x005f }
            r29.<init>()     // Catch:{ IllegalArgumentException -> 0x005f }
            r4 = r29
        L_0x014c:
            int r20 = r41.size()     // Catch:{ IllegalArgumentException -> 0x005f }
            if (r40 == 0) goto L_0x0159
            r0 = r41
            r1 = r40
            r0.add(r1)     // Catch:{ IllegalArgumentException -> 0x005f }
        L_0x0159:
            if (r27 == 0) goto L_0x01a4
            java.lang.Class<com.google.api.client.util.GenericData> r5 = com.google.api.client.util.GenericData.class
            r0 = r37
            boolean r5 = r5.isAssignableFrom(r0)     // Catch:{ IllegalArgumentException -> 0x005f }
            if (r5 != 0) goto L_0x01a4
            java.lang.Class<java.util.Map> r5 = java.util.Map.class
            r0 = r37
            boolean r5 = r5.isAssignableFrom(r0)     // Catch:{ IllegalArgumentException -> 0x005f }
            if (r5 == 0) goto L_0x01a2
            java.lang.reflect.Type r11 = com.google.api.client.util.Types.getMapValueParameter(r40)     // Catch:{ IllegalArgumentException -> 0x005f }
        L_0x0173:
            if (r11 == 0) goto L_0x01a4
            r0 = r4
            java.util.Map r0 = (java.util.Map) r0     // Catch:{ IllegalArgumentException -> 0x005f }
            r10 = r0
            r8 = r38
            r9 = r39
            r12 = r41
            r13 = r43
            r8.parseMap(r9, r10, r11, r12, r13)     // Catch:{ IllegalArgumentException -> 0x005f }
            goto L_0x002c
        L_0x0186:
            r4 = 0
            goto L_0x0114
        L_0x0188:
            r36 = 0
            goto L_0x0125
        L_0x018b:
            r27 = 0
            goto L_0x0143
        L_0x018e:
            if (r29 != 0) goto L_0x040c
            if (r27 != 0) goto L_0x0194
            if (r37 != 0) goto L_0x019b
        L_0x0194:
            java.util.Map r29 = com.google.api.client.util.Data.newMapInstance(r37)     // Catch:{ IllegalArgumentException -> 0x005f }
            r4 = r29
            goto L_0x014c
        L_0x019b:
            java.lang.Object r29 = com.google.api.client.util.Types.newInstance(r37)     // Catch:{ IllegalArgumentException -> 0x005f }
            r4 = r29
            goto L_0x014c
        L_0x01a2:
            r11 = 0
            goto L_0x0173
        L_0x01a4:
            r0 = r38
            r1 = r41
            r2 = r43
            r0.parse(r1, r4, r2)     // Catch:{ IllegalArgumentException -> 0x005f }
            if (r40 == 0) goto L_0x01b6
            r0 = r41
            r1 = r20
            r0.remove(r1)     // Catch:{ IllegalArgumentException -> 0x005f }
        L_0x01b6:
            if (r36 == 0) goto L_0x002c
            r0 = r4
            com.google.api.client.json.GenericJson r0 = (com.google.api.client.json.GenericJson) r0     // Catch:{ IllegalArgumentException -> 0x005f }
            r5 = r0
            java.lang.String r8 = r36.getName()     // Catch:{ IllegalArgumentException -> 0x005f }
            java.lang.Object r35 = r5.get(r8)     // Catch:{ IllegalArgumentException -> 0x005f }
            if (r35 == 0) goto L_0x023c
            r5 = 1
        L_0x01c7:
            java.lang.String r8 = "No value specified for @JsonPolymorphicTypeMap field"
            com.google.api.client.util.Preconditions.checkArgument(r5, r8)     // Catch:{ IllegalArgumentException -> 0x005f }
            java.lang.String r34 = r35.toString()     // Catch:{ IllegalArgumentException -> 0x005f }
            java.lang.Class<com.google.api.client.json.JsonPolymorphicTypeMap> r5 = com.google.api.client.json.JsonPolymorphicTypeMap.class
            r0 = r36
            java.lang.annotation.Annotation r33 = r0.getAnnotation(r5)     // Catch:{ IllegalArgumentException -> 0x005f }
            com.google.api.client.json.JsonPolymorphicTypeMap r33 = (com.google.api.client.json.JsonPolymorphicTypeMap) r33     // Catch:{ IllegalArgumentException -> 0x005f }
            r14 = 0
            com.google.api.client.json.JsonPolymorphicTypeMap$TypeDef[] r19 = r33.typeDefinitions()     // Catch:{ IllegalArgumentException -> 0x005f }
            r0 = r19
            int r0 = r0.length     // Catch:{ IllegalArgumentException -> 0x005f }
            r28 = r0
            r25 = 0
        L_0x01e6:
            r0 = r25
            r1 = r28
            if (r0 >= r1) goto L_0x01fe
            r32 = r19[r25]     // Catch:{ IllegalArgumentException -> 0x005f }
            java.lang.String r5 = r32.key()     // Catch:{ IllegalArgumentException -> 0x005f }
            r0 = r34
            boolean r5 = r5.equals(r0)     // Catch:{ IllegalArgumentException -> 0x005f }
            if (r5 == 0) goto L_0x023e
            java.lang.Class r14 = r32.ref()     // Catch:{ IllegalArgumentException -> 0x005f }
        L_0x01fe:
            if (r14 == 0) goto L_0x0241
            r5 = 1
        L_0x0201:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ IllegalArgumentException -> 0x005f }
            r8.<init>()     // Catch:{ IllegalArgumentException -> 0x005f }
            java.lang.String r9 = "No TypeDef annotation found with key: "
            java.lang.StringBuilder r8 = r8.append(r9)     // Catch:{ IllegalArgumentException -> 0x005f }
            r0 = r34
            java.lang.StringBuilder r8 = r8.append(r0)     // Catch:{ IllegalArgumentException -> 0x005f }
            java.lang.String r8 = r8.toString()     // Catch:{ IllegalArgumentException -> 0x005f }
            com.google.api.client.util.Preconditions.checkArgument(r5, r8)     // Catch:{ IllegalArgumentException -> 0x005f }
            com.google.api.client.json.JsonFactory r24 = r38.getFactory()     // Catch:{ IllegalArgumentException -> 0x005f }
            r0 = r24
            java.lang.String r4 = r0.toString(r4)     // Catch:{ IllegalArgumentException -> 0x005f }
            r0 = r24
            com.google.api.client.json.JsonParser r12 = r0.createJsonParser(r4)     // Catch:{ IllegalArgumentException -> 0x005f }
            r12.startParsing()     // Catch:{ IllegalArgumentException -> 0x005f }
            r16 = 0
            r17 = 0
            r18 = 0
            r13 = r39
            r15 = r41
            java.lang.Object r4 = r12.parseValue(r13, r14, r15, r16, r17, r18)     // Catch:{ IllegalArgumentException -> 0x005f }
            goto L_0x002c
        L_0x023c:
            r5 = 0
            goto L_0x01c7
        L_0x023e:
            int r25 = r25 + 1
            goto L_0x01e6
        L_0x0241:
            r5 = 0
            goto L_0x0201
        L_0x0243:
            if (r40 == 0) goto L_0x0257
            java.lang.Class r4 = java.lang.Boolean.TYPE     // Catch:{ IllegalArgumentException -> 0x005f }
            r0 = r37
            if (r0 == r4) goto L_0x0257
            if (r37 == 0) goto L_0x026d
            java.lang.Class<java.lang.Boolean> r4 = java.lang.Boolean.class
            r0 = r37
            boolean r4 = r0.isAssignableFrom(r4)     // Catch:{ IllegalArgumentException -> 0x005f }
            if (r4 == 0) goto L_0x026d
        L_0x0257:
            r4 = 1
        L_0x0258:
            java.lang.String r5 = "expected type Boolean or boolean but got %s"
            r8 = 1
            java.lang.Object[] r8 = new java.lang.Object[r8]     // Catch:{ IllegalArgumentException -> 0x005f }
            r9 = 0
            r8[r9] = r40     // Catch:{ IllegalArgumentException -> 0x005f }
            com.google.api.client.util.Preconditions.checkArgument(r4, r5, r8)     // Catch:{ IllegalArgumentException -> 0x005f }
            com.google.api.client.json.JsonToken r4 = com.google.api.client.json.JsonToken.VALUE_TRUE     // Catch:{ IllegalArgumentException -> 0x005f }
            r0 = r31
            if (r0 != r4) goto L_0x026f
            java.lang.Boolean r4 = java.lang.Boolean.TRUE     // Catch:{ IllegalArgumentException -> 0x005f }
            goto L_0x002c
        L_0x026d:
            r4 = 0
            goto L_0x0258
        L_0x026f:
            java.lang.Boolean r4 = java.lang.Boolean.FALSE     // Catch:{ IllegalArgumentException -> 0x005f }
            goto L_0x002c
        L_0x0273:
            if (r39 == 0) goto L_0x027f
            java.lang.Class<com.google.api.client.json.JsonString> r4 = com.google.api.client.json.JsonString.class
            r0 = r39
            java.lang.annotation.Annotation r4 = r0.getAnnotation(r4)     // Catch:{ IllegalArgumentException -> 0x005f }
            if (r4 != 0) goto L_0x0297
        L_0x027f:
            r4 = 1
        L_0x0280:
            java.lang.String r5 = "number type formatted as a JSON number cannot use @JsonString annotation"
            com.google.api.client.util.Preconditions.checkArgument(r4, r5)     // Catch:{ IllegalArgumentException -> 0x005f }
            if (r37 == 0) goto L_0x0291
            java.lang.Class<java.math.BigDecimal> r4 = java.math.BigDecimal.class
            r0 = r37
            boolean r4 = r0.isAssignableFrom(r4)     // Catch:{ IllegalArgumentException -> 0x005f }
            if (r4 == 0) goto L_0x0299
        L_0x0291:
            java.math.BigDecimal r4 = r38.getDecimalValue()     // Catch:{ IllegalArgumentException -> 0x005f }
            goto L_0x002c
        L_0x0297:
            r4 = 0
            goto L_0x0280
        L_0x0299:
            java.lang.Class<java.math.BigInteger> r4 = java.math.BigInteger.class
            r0 = r37
            if (r0 != r4) goto L_0x02a5
            java.math.BigInteger r4 = r38.getBigIntegerValue()     // Catch:{ IllegalArgumentException -> 0x005f }
            goto L_0x002c
        L_0x02a5:
            java.lang.Class<java.lang.Double> r4 = java.lang.Double.class
            r0 = r37
            if (r0 == r4) goto L_0x02b1
            java.lang.Class r4 = java.lang.Double.TYPE     // Catch:{ IllegalArgumentException -> 0x005f }
            r0 = r37
            if (r0 != r4) goto L_0x02bb
        L_0x02b1:
            double r4 = r38.getDoubleValue()     // Catch:{ IllegalArgumentException -> 0x005f }
            java.lang.Double r4 = java.lang.Double.valueOf(r4)     // Catch:{ IllegalArgumentException -> 0x005f }
            goto L_0x002c
        L_0x02bb:
            java.lang.Class<java.lang.Long> r4 = java.lang.Long.class
            r0 = r37
            if (r0 == r4) goto L_0x02c7
            java.lang.Class r4 = java.lang.Long.TYPE     // Catch:{ IllegalArgumentException -> 0x005f }
            r0 = r37
            if (r0 != r4) goto L_0x02d1
        L_0x02c7:
            long r4 = r38.getLongValue()     // Catch:{ IllegalArgumentException -> 0x005f }
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ IllegalArgumentException -> 0x005f }
            goto L_0x002c
        L_0x02d1:
            java.lang.Class<java.lang.Float> r4 = java.lang.Float.class
            r0 = r37
            if (r0 == r4) goto L_0x02dd
            java.lang.Class r4 = java.lang.Float.TYPE     // Catch:{ IllegalArgumentException -> 0x005f }
            r0 = r37
            if (r0 != r4) goto L_0x02e7
        L_0x02dd:
            float r4 = r38.getFloatValue()     // Catch:{ IllegalArgumentException -> 0x005f }
            java.lang.Float r4 = java.lang.Float.valueOf(r4)     // Catch:{ IllegalArgumentException -> 0x005f }
            goto L_0x002c
        L_0x02e7:
            java.lang.Class<java.lang.Integer> r4 = java.lang.Integer.class
            r0 = r37
            if (r0 == r4) goto L_0x02f3
            java.lang.Class r4 = java.lang.Integer.TYPE     // Catch:{ IllegalArgumentException -> 0x005f }
            r0 = r37
            if (r0 != r4) goto L_0x02fd
        L_0x02f3:
            int r4 = r38.getIntValue()     // Catch:{ IllegalArgumentException -> 0x005f }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ IllegalArgumentException -> 0x005f }
            goto L_0x002c
        L_0x02fd:
            java.lang.Class<java.lang.Short> r4 = java.lang.Short.class
            r0 = r37
            if (r0 == r4) goto L_0x0309
            java.lang.Class r4 = java.lang.Short.TYPE     // Catch:{ IllegalArgumentException -> 0x005f }
            r0 = r37
            if (r0 != r4) goto L_0x0313
        L_0x0309:
            short r4 = r38.getShortValue()     // Catch:{ IllegalArgumentException -> 0x005f }
            java.lang.Short r4 = java.lang.Short.valueOf(r4)     // Catch:{ IllegalArgumentException -> 0x005f }
            goto L_0x002c
        L_0x0313:
            java.lang.Class<java.lang.Byte> r4 = java.lang.Byte.class
            r0 = r37
            if (r0 == r4) goto L_0x031f
            java.lang.Class r4 = java.lang.Byte.TYPE     // Catch:{ IllegalArgumentException -> 0x005f }
            r0 = r37
            if (r0 != r4) goto L_0x0329
        L_0x031f:
            byte r4 = r38.getByteValue()     // Catch:{ IllegalArgumentException -> 0x005f }
            java.lang.Byte r4 = java.lang.Byte.valueOf(r4)     // Catch:{ IllegalArgumentException -> 0x005f }
            goto L_0x002c
        L_0x0329:
            java.lang.IllegalArgumentException r4 = new java.lang.IllegalArgumentException     // Catch:{ IllegalArgumentException -> 0x005f }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ IllegalArgumentException -> 0x005f }
            r5.<init>()     // Catch:{ IllegalArgumentException -> 0x005f }
            java.lang.String r8 = "expected numeric type but got "
            java.lang.StringBuilder r5 = r5.append(r8)     // Catch:{ IllegalArgumentException -> 0x005f }
            r0 = r40
            java.lang.StringBuilder r5 = r5.append(r0)     // Catch:{ IllegalArgumentException -> 0x005f }
            java.lang.String r5 = r5.toString()     // Catch:{ IllegalArgumentException -> 0x005f }
            r4.<init>(r5)     // Catch:{ IllegalArgumentException -> 0x005f }
            throw r4     // Catch:{ IllegalArgumentException -> 0x005f }
        L_0x0344:
            java.lang.String r4 = r38.getText()     // Catch:{ IllegalArgumentException -> 0x005f }
            java.lang.String r4 = r4.trim()     // Catch:{ IllegalArgumentException -> 0x005f }
            java.util.Locale r5 = java.util.Locale.US     // Catch:{ IllegalArgumentException -> 0x005f }
            java.lang.String r30 = r4.toLowerCase(r5)     // Catch:{ IllegalArgumentException -> 0x005f }
            java.lang.Class r4 = java.lang.Float.TYPE     // Catch:{ IllegalArgumentException -> 0x005f }
            r0 = r37
            if (r0 == r4) goto L_0x036a
            java.lang.Class<java.lang.Float> r4 = java.lang.Float.class
            r0 = r37
            if (r0 == r4) goto L_0x036a
            java.lang.Class r4 = java.lang.Double.TYPE     // Catch:{ IllegalArgumentException -> 0x005f }
            r0 = r37
            if (r0 == r4) goto L_0x036a
            java.lang.Class<java.lang.Double> r4 = java.lang.Double.class
            r0 = r37
            if (r0 != r4) goto L_0x0388
        L_0x036a:
            java.lang.String r4 = "nan"
            r0 = r30
            boolean r4 = r0.equals(r4)     // Catch:{ IllegalArgumentException -> 0x005f }
            if (r4 != 0) goto L_0x03a6
            java.lang.String r4 = "infinity"
            r0 = r30
            boolean r4 = r0.equals(r4)     // Catch:{ IllegalArgumentException -> 0x005f }
            if (r4 != 0) goto L_0x03a6
            java.lang.String r4 = "-infinity"
            r0 = r30
            boolean r4 = r0.equals(r4)     // Catch:{ IllegalArgumentException -> 0x005f }
            if (r4 != 0) goto L_0x03a6
        L_0x0388:
            if (r37 == 0) goto L_0x03a0
            java.lang.Class<java.lang.Number> r4 = java.lang.Number.class
            r0 = r37
            boolean r4 = r4.isAssignableFrom(r0)     // Catch:{ IllegalArgumentException -> 0x005f }
            if (r4 == 0) goto L_0x03a0
            if (r39 == 0) goto L_0x03b2
            java.lang.Class<com.google.api.client.json.JsonString> r4 = com.google.api.client.json.JsonString.class
            r0 = r39
            java.lang.annotation.Annotation r4 = r0.getAnnotation(r4)     // Catch:{ IllegalArgumentException -> 0x005f }
            if (r4 == 0) goto L_0x03b2
        L_0x03a0:
            r4 = 1
        L_0x03a1:
            java.lang.String r5 = "number field formatted as a JSON string must use the @JsonString annotation"
            com.google.api.client.util.Preconditions.checkArgument(r4, r5)     // Catch:{ IllegalArgumentException -> 0x005f }
        L_0x03a6:
            java.lang.String r4 = r38.getText()     // Catch:{ IllegalArgumentException -> 0x005f }
            r0 = r40
            java.lang.Object r4 = com.google.api.client.util.Data.parsePrimitiveValue(r0, r4)     // Catch:{ IllegalArgumentException -> 0x005f }
            goto L_0x002c
        L_0x03b2:
            r4 = 0
            goto L_0x03a1
        L_0x03b4:
            if (r37 == 0) goto L_0x03bc
            boolean r4 = r37.isPrimitive()     // Catch:{ IllegalArgumentException -> 0x005f }
            if (r4 != 0) goto L_0x03e4
        L_0x03bc:
            r4 = 1
        L_0x03bd:
            java.lang.String r5 = "primitive number field but found a JSON null"
            com.google.api.client.util.Preconditions.checkArgument(r4, r5)     // Catch:{ IllegalArgumentException -> 0x005f }
            if (r37 == 0) goto L_0x03fe
            int r4 = r37.getModifiers()     // Catch:{ IllegalArgumentException -> 0x005f }
            r4 = r4 & 1536(0x600, float:2.152E-42)
            if (r4 == 0) goto L_0x03fe
            java.lang.Class<java.util.Collection> r4 = java.util.Collection.class
            r0 = r37
            boolean r4 = com.google.api.client.util.Types.isAssignableToOrFrom(r0, r4)     // Catch:{ IllegalArgumentException -> 0x005f }
            if (r4 == 0) goto L_0x03e6
            java.util.Collection r4 = com.google.api.client.util.Data.newCollectionInstance(r40)     // Catch:{ IllegalArgumentException -> 0x005f }
            java.lang.Class r4 = r4.getClass()     // Catch:{ IllegalArgumentException -> 0x005f }
            java.lang.Object r4 = com.google.api.client.util.Data.nullOf(r4)     // Catch:{ IllegalArgumentException -> 0x005f }
            goto L_0x002c
        L_0x03e4:
            r4 = 0
            goto L_0x03bd
        L_0x03e6:
            java.lang.Class<java.util.Map> r4 = java.util.Map.class
            r0 = r37
            boolean r4 = com.google.api.client.util.Types.isAssignableToOrFrom(r0, r4)     // Catch:{ IllegalArgumentException -> 0x005f }
            if (r4 == 0) goto L_0x03fe
            java.util.Map r4 = com.google.api.client.util.Data.newMapInstance(r37)     // Catch:{ IllegalArgumentException -> 0x005f }
            java.lang.Class r4 = r4.getClass()     // Catch:{ IllegalArgumentException -> 0x005f }
            java.lang.Object r4 = com.google.api.client.util.Data.nullOf(r4)     // Catch:{ IllegalArgumentException -> 0x005f }
            goto L_0x002c
        L_0x03fe:
            r0 = r41
            r1 = r40
            java.lang.Class r4 = com.google.api.client.util.Types.getRawArrayComponentType(r0, r1)     // Catch:{ IllegalArgumentException -> 0x005f }
            java.lang.Object r4 = com.google.api.client.util.Data.nullOf(r4)     // Catch:{ IllegalArgumentException -> 0x005f }
            goto L_0x002c
        L_0x040c:
            r4 = r29
            goto L_0x014c
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.api.client.json.JsonParser.parseValue(java.lang.reflect.Field, java.lang.reflect.Type, java.util.ArrayList, java.lang.Object, com.google.api.client.json.CustomizeJsonParser, boolean):java.lang.Object");
    }

    private static Field getCachedTypemapFieldFor(Class<?> key) {
        TypeDef[] arr$;
        if (key == null) {
            return null;
        }
        lock.lock();
        try {
            if (cachedTypemapFields.containsKey(key)) {
                return (Field) cachedTypemapFields.get(key);
            }
            Field value = null;
            for (FieldInfo fieldInfo : ClassInfo.m1470of(key).getFieldInfos()) {
                Field field = fieldInfo.getField();
                JsonPolymorphicTypeMap typemapAnnotation = (JsonPolymorphicTypeMap) field.getAnnotation(JsonPolymorphicTypeMap.class);
                if (typemapAnnotation != null) {
                    Preconditions.checkArgument(value == null, "Class contains more than one field with @JsonPolymorphicTypeMap annotation: %s", key);
                    Preconditions.checkArgument(Data.isPrimitive(field.getType()), "Field which has the @JsonPolymorphicTypeMap, %s, is not a supported type: %s", key, field.getType());
                    value = field;
                    TypeDef[] typeDefs = typemapAnnotation.typeDefinitions();
                    HashSet<String> typeDefKeys = Sets.newHashSet();
                    Preconditions.checkArgument(typeDefs.length > 0, "@JsonPolymorphicTypeMap must have at least one @TypeDef");
                    for (TypeDef typeDef : typeDefs) {
                        Preconditions.checkArgument(typeDefKeys.add(typeDef.key()), "Class contains two @TypeDef annotations with identical key: %s", typeDef.key());
                    }
                }
            }
            cachedTypemapFields.put(key, value);
            lock.unlock();
            return value;
        } finally {
            lock.unlock();
        }
    }
}
