package com.google.common.reflect;

import com.google.common.collect.Sets;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Set;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
abstract class TypeVisitor {
    private final Set<Type> visited = Sets.newHashSet();

    TypeVisitor() {
    }

    public final void visit(Type... types) {
        Type[] arr$ = types;
        int len$ = arr$.length;
        for (int i$ = 0; i$ < len$; i$++) {
            Type type = arr$[i$];
            if (type != null && this.visited.add(type)) {
                boolean succeeded = false;
                try {
                    if (type instanceof TypeVariable) {
                        visitTypeVariable((TypeVariable) type);
                    } else if (type instanceof WildcardType) {
                        visitWildcardType((WildcardType) type);
                    } else if (type instanceof ParameterizedType) {
                        visitParameterizedType((ParameterizedType) type);
                    } else if (type instanceof Class) {
                        visitClass((Class) type);
                    } else if (type instanceof GenericArrayType) {
                        visitGenericArrayType((GenericArrayType) type);
                    } else {
                        throw new AssertionError("Unknown type: " + type);
                    }
                    succeeded = true;
                } finally {
                    if (!succeeded) {
                        this.visited.remove(type);
                    }
                }
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void visitClass(Class<?> cls) {
    }

    /* access modifiers changed from: 0000 */
    public void visitGenericArrayType(GenericArrayType t) {
    }

    /* access modifiers changed from: 0000 */
    public void visitParameterizedType(ParameterizedType t) {
    }

    /* access modifiers changed from: 0000 */
    public void visitTypeVariable(TypeVariable<?> typeVariable) {
    }

    /* access modifiers changed from: 0000 */
    public void visitWildcardType(WildcardType t) {
    }
}
