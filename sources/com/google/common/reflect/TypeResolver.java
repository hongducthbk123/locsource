package com.google.common.reflect;

import com.google.common.annotations.Beta;
import com.google.common.base.Joiner;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import com.google.common.collect.Maps;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Nullable;

@Beta
public final class TypeResolver {
    private final TypeTable typeTable;

    private static final class TypeMappingIntrospector extends TypeVisitor {
        private static final WildcardCapturer wildcardCapturer = new WildcardCapturer();
        private final Map<TypeVariableKey, Type> mappings = Maps.newHashMap();

        private TypeMappingIntrospector() {
        }

        static ImmutableMap<TypeVariableKey, Type> getTypeMappings(Type contextType) {
            TypeMappingIntrospector introspector = new TypeMappingIntrospector();
            introspector.visit(wildcardCapturer.capture(contextType));
            return ImmutableMap.copyOf(introspector.mappings);
        }

        /* access modifiers changed from: 0000 */
        public void visitClass(Class<?> clazz) {
            visit(clazz.getGenericSuperclass());
            visit(clazz.getGenericInterfaces());
        }

        /* access modifiers changed from: 0000 */
        public void visitParameterizedType(ParameterizedType parameterizedType) {
            boolean z;
            Class<?> rawClass = (Class) parameterizedType.getRawType();
            TypeVariable<?>[] vars = rawClass.getTypeParameters();
            Type[] typeArgs = parameterizedType.getActualTypeArguments();
            if (vars.length == typeArgs.length) {
                z = true;
            } else {
                z = false;
            }
            Preconditions.checkState(z);
            for (int i = 0; i < vars.length; i++) {
                map(new TypeVariableKey(vars[i]), typeArgs[i]);
            }
            visit(rawClass);
            visit(parameterizedType.getOwnerType());
        }

        /* access modifiers changed from: 0000 */
        public void visitTypeVariable(TypeVariable<?> t) {
            visit(t.getBounds());
        }

        /* access modifiers changed from: 0000 */
        public void visitWildcardType(WildcardType t) {
            visit(t.getUpperBounds());
        }

        private void map(TypeVariableKey var, Type arg) {
            if (!this.mappings.containsKey(var)) {
                Type t = arg;
                while (t != null) {
                    if (var.equalsType(t)) {
                        Type x = arg;
                        while (x != null) {
                            x = (Type) this.mappings.remove(TypeVariableKey.forLookup(x));
                        }
                        return;
                    }
                    t = (Type) this.mappings.get(TypeVariableKey.forLookup(t));
                }
                this.mappings.put(var, arg);
            }
        }
    }

    private static class TypeTable {
        private final ImmutableMap<TypeVariableKey, Type> map;

        TypeTable() {
            this.map = ImmutableMap.m1530of();
        }

        private TypeTable(ImmutableMap<TypeVariableKey, Type> map2) {
            this.map = map2;
        }

        /* access modifiers changed from: 0000 */
        public final TypeTable where(Map<TypeVariableKey, ? extends Type> mappings) {
            boolean z;
            Builder<TypeVariableKey, Type> builder = ImmutableMap.builder();
            builder.putAll(this.map);
            for (Entry<TypeVariableKey, ? extends Type> mapping : mappings.entrySet()) {
                TypeVariableKey variable = (TypeVariableKey) mapping.getKey();
                Type type = (Type) mapping.getValue();
                if (!variable.equalsType(type)) {
                    z = true;
                } else {
                    z = false;
                }
                Preconditions.checkArgument(z, "Type variable %s bound to itself", variable);
                builder.put(variable, type);
            }
            return new TypeTable(builder.build());
        }

        /* access modifiers changed from: 0000 */
        public final Type resolve(final TypeVariable<?> var) {
            return resolveInternal(var, new TypeTable() {
                public Type resolveInternal(TypeVariable<?> intermediateVar, TypeTable forDependent) {
                    return intermediateVar.getGenericDeclaration().equals(var.getGenericDeclaration()) ? intermediateVar : this.resolveInternal(intermediateVar, forDependent);
                }
            });
        }

        /* access modifiers changed from: 0000 */
        public Type resolveInternal(TypeVariable<?> var, TypeTable forDependants) {
            Type type = (Type) this.map.get(new TypeVariableKey(var));
            if (type != null) {
                return new TypeResolver(forDependants).resolveType(type);
            }
            Type[] bounds = var.getBounds();
            if (bounds.length == 0) {
                return var;
            }
            Type[] resolvedBounds = new TypeResolver(forDependants).resolveTypes(bounds);
            if (!NativeTypeVariableEquals.NATIVE_TYPE_VARIABLE_ONLY || !Arrays.equals(bounds, resolvedBounds)) {
                return Types.newArtificialTypeVariable(var.getGenericDeclaration(), var.getName(), resolvedBounds);
            }
            return var;
        }
    }

    static final class TypeVariableKey {
        private final TypeVariable<?> var;

        TypeVariableKey(TypeVariable<?> var2) {
            this.var = (TypeVariable) Preconditions.checkNotNull(var2);
        }

        public int hashCode() {
            return Objects.hashCode(this.var.getGenericDeclaration(), this.var.getName());
        }

        public boolean equals(Object obj) {
            if (obj instanceof TypeVariableKey) {
                return equalsTypeVariable(((TypeVariableKey) obj).var);
            }
            return false;
        }

        public String toString() {
            return this.var.toString();
        }

        static Object forLookup(Type t) {
            if (t instanceof TypeVariable) {
                return new TypeVariableKey((TypeVariable) t);
            }
            return null;
        }

        /* access modifiers changed from: 0000 */
        public boolean equalsType(Type type) {
            if (type instanceof TypeVariable) {
                return equalsTypeVariable((TypeVariable) type);
            }
            return false;
        }

        private boolean equalsTypeVariable(TypeVariable<?> that) {
            return this.var.getGenericDeclaration().equals(that.getGenericDeclaration()) && this.var.getName().equals(that.getName());
        }
    }

    private static final class WildcardCapturer {

        /* renamed from: id */
        private final AtomicInteger f1033id;

        private WildcardCapturer() {
            this.f1033id = new AtomicInteger();
        }

        /* access modifiers changed from: 0000 */
        public Type capture(Type type) {
            Preconditions.checkNotNull(type);
            if ((type instanceof Class) || (type instanceof TypeVariable)) {
                return type;
            }
            if (type instanceof GenericArrayType) {
                return Types.newArrayType(capture(((GenericArrayType) type).getGenericComponentType()));
            }
            if (type instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) type;
                return Types.newParameterizedTypeWithOwner(captureNullable(parameterizedType.getOwnerType()), (Class) parameterizedType.getRawType(), capture(parameterizedType.getActualTypeArguments()));
            } else if (type instanceof WildcardType) {
                WildcardType wildcardType = (WildcardType) type;
                if (wildcardType.getLowerBounds().length != 0) {
                    return type;
                }
                return Types.newArtificialTypeVariable(WildcardCapturer.class, "capture#" + this.f1033id.incrementAndGet() + "-of ? extends " + Joiner.m1485on('&').join((Object[]) wildcardType.getUpperBounds()), wildcardType.getUpperBounds());
            } else {
                throw new AssertionError("must have been one of the known types");
            }
        }

        private Type captureNullable(@Nullable Type type) {
            if (type == null) {
                return null;
            }
            return capture(type);
        }

        private Type[] capture(Type[] types) {
            Type[] result = new Type[types.length];
            for (int i = 0; i < types.length; i++) {
                result[i] = capture(types[i]);
            }
            return result;
        }
    }

    public TypeResolver() {
        this.typeTable = new TypeTable();
    }

    private TypeResolver(TypeTable typeTable2) {
        this.typeTable = typeTable2;
    }

    static TypeResolver accordingTo(Type type) {
        return new TypeResolver().where(TypeMappingIntrospector.getTypeMappings(type));
    }

    public TypeResolver where(Type formal, Type actual) {
        Map<TypeVariableKey, Type> mappings = Maps.newHashMap();
        populateTypeMappings(mappings, (Type) Preconditions.checkNotNull(formal), (Type) Preconditions.checkNotNull(actual));
        return where(mappings);
    }

    /* access modifiers changed from: 0000 */
    public TypeResolver where(Map<TypeVariableKey, ? extends Type> mappings) {
        return new TypeResolver(this.typeTable.where(mappings));
    }

    /* access modifiers changed from: private */
    public static void populateTypeMappings(final Map<TypeVariableKey, Type> mappings, Type from, final Type to) {
        if (!from.equals(to)) {
            new TypeVisitor() {
                /* access modifiers changed from: 0000 */
                public void visitTypeVariable(TypeVariable<?> typeVariable) {
                    mappings.put(new TypeVariableKey(typeVariable), to);
                }

                /* access modifiers changed from: 0000 */
                public void visitWildcardType(WildcardType fromWildcardType) {
                    boolean z;
                    WildcardType toWildcardType = (WildcardType) TypeResolver.expectArgument(WildcardType.class, to);
                    Type[] fromUpperBounds = fromWildcardType.getUpperBounds();
                    Type[] toUpperBounds = toWildcardType.getUpperBounds();
                    Type[] fromLowerBounds = fromWildcardType.getLowerBounds();
                    Type[] toLowerBounds = toWildcardType.getLowerBounds();
                    if (fromUpperBounds.length == toUpperBounds.length && fromLowerBounds.length == toLowerBounds.length) {
                        z = true;
                    } else {
                        z = false;
                    }
                    Preconditions.checkArgument(z, "Incompatible type: %s vs. %s", fromWildcardType, to);
                    for (int i = 0; i < fromUpperBounds.length; i++) {
                        TypeResolver.populateTypeMappings(mappings, fromUpperBounds[i], toUpperBounds[i]);
                    }
                    for (int i2 = 0; i2 < fromLowerBounds.length; i2++) {
                        TypeResolver.populateTypeMappings(mappings, fromLowerBounds[i2], toLowerBounds[i2]);
                    }
                }

                /* access modifiers changed from: 0000 */
                public void visitParameterizedType(ParameterizedType fromParameterizedType) {
                    boolean z;
                    ParameterizedType toParameterizedType = (ParameterizedType) TypeResolver.expectArgument(ParameterizedType.class, to);
                    Preconditions.checkArgument(fromParameterizedType.getRawType().equals(toParameterizedType.getRawType()), "Inconsistent raw type: %s vs. %s", fromParameterizedType, to);
                    Type[] fromArgs = fromParameterizedType.getActualTypeArguments();
                    Type[] toArgs = toParameterizedType.getActualTypeArguments();
                    if (fromArgs.length == toArgs.length) {
                        z = true;
                    } else {
                        z = false;
                    }
                    Preconditions.checkArgument(z, "%s not compatible with %s", fromParameterizedType, toParameterizedType);
                    for (int i = 0; i < fromArgs.length; i++) {
                        TypeResolver.populateTypeMappings(mappings, fromArgs[i], toArgs[i]);
                    }
                }

                /* access modifiers changed from: 0000 */
                public void visitGenericArrayType(GenericArrayType fromArrayType) {
                    boolean z;
                    Type componentType = Types.getComponentType(to);
                    if (componentType != null) {
                        z = true;
                    } else {
                        z = false;
                    }
                    Preconditions.checkArgument(z, "%s is not an array type.", to);
                    TypeResolver.populateTypeMappings(mappings, fromArrayType.getGenericComponentType(), componentType);
                }

                /* access modifiers changed from: 0000 */
                public void visitClass(Class<?> fromClass) {
                    throw new IllegalArgumentException("No type mapping from " + fromClass);
                }
            }.visit(from);
        }
    }

    public Type resolveType(Type type) {
        Preconditions.checkNotNull(type);
        if (type instanceof TypeVariable) {
            return this.typeTable.resolve((TypeVariable) type);
        }
        if (type instanceof ParameterizedType) {
            return resolveParameterizedType((ParameterizedType) type);
        }
        if (type instanceof GenericArrayType) {
            return resolveGenericArrayType((GenericArrayType) type);
        }
        if (type instanceof WildcardType) {
            return resolveWildcardType((WildcardType) type);
        }
        return type;
    }

    /* access modifiers changed from: private */
    public Type[] resolveTypes(Type[] types) {
        Type[] result = new Type[types.length];
        for (int i = 0; i < types.length; i++) {
            result[i] = resolveType(types[i]);
        }
        return result;
    }

    private WildcardType resolveWildcardType(WildcardType type) {
        return new WildcardTypeImpl(resolveTypes(type.getLowerBounds()), resolveTypes(type.getUpperBounds()));
    }

    private Type resolveGenericArrayType(GenericArrayType type) {
        return Types.newArrayType(resolveType(type.getGenericComponentType()));
    }

    private ParameterizedType resolveParameterizedType(ParameterizedType type) {
        Type owner = type.getOwnerType();
        return Types.newParameterizedTypeWithOwner(owner == null ? null : resolveType(owner), (Class) resolveType(type.getRawType()), resolveTypes(type.getActualTypeArguments()));
    }

    /* access modifiers changed from: private */
    public static <T> T expectArgument(Class<T> type, Object arg) {
        try {
            return type.cast(arg);
        } catch (ClassCastException e) {
            throw new IllegalArgumentException(arg + " is not a " + type.getSimpleName());
        }
    }
}
