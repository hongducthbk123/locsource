package com.sensorsdata.analytics.android.sdk;

import android.view.View;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class Caller {
    private static final String TAG = "SA.Caller";
    private final Object[] mMethodArgs;
    private final String mMethodName;
    private final Class<?> mMethodResultType;
    private final Class<?> mTargetClass;
    private final Method mTargetMethod;

    public Caller(Class<?> targetClass, String methodName, Object[] methodArgs, Class<?> resultType) throws NoSuchMethodException {
        this.mMethodName = methodName;
        this.mMethodArgs = methodArgs;
        this.mMethodResultType = resultType;
        this.mTargetMethod = pickMethod(targetClass);
        if (this.mTargetMethod == null) {
            throw new NoSuchMethodException("Method " + targetClass.getName() + "." + this.mMethodName + " doesn't exit");
        }
        this.mTargetClass = this.mTargetMethod.getDeclaringClass();
    }

    public String toString() {
        return "[Caller " + this.mMethodName + "(" + this.mMethodArgs + ")]";
    }

    public Object[] getArgs() {
        return this.mMethodArgs;
    }

    public Object applyMethod(View target) {
        return applyMethodWithArguments(target, this.mMethodArgs);
    }

    public Object applyMethodWithArguments(View target, Object[] arguments) {
        if (this.mTargetClass.isAssignableFrom(target.getClass())) {
            try {
                return this.mTargetMethod.invoke(target, arguments);
            } catch (IllegalAccessException e) {
                SALog.m1609i(TAG, "Method " + this.mTargetMethod.getName() + " appears not to be public", e);
            } catch (IllegalArgumentException e2) {
                SALog.m1609i(TAG, "Method " + this.mTargetMethod.getName() + " called with arguments of the wrong type", e2);
            } catch (InvocationTargetException e3) {
                SALog.m1609i(TAG, "Method " + this.mTargetMethod.getName() + " threw an exception", e3);
            }
        }
        return null;
    }

    public boolean argsAreApplicable(Object[] proposedArgs) {
        Class<?>[] paramTypes = this.mTargetMethod.getParameterTypes();
        if (proposedArgs.length != paramTypes.length) {
            return false;
        }
        for (int i = 0; i < proposedArgs.length; i++) {
            Class<?> paramType = assignableArgType(paramTypes[i]);
            if (proposedArgs[i] == null) {
                if (paramType == Byte.TYPE || paramType == Short.TYPE || paramType == Integer.TYPE || paramType == Long.TYPE || paramType == Float.TYPE || paramType == Double.TYPE || paramType == Boolean.TYPE || paramType == Character.TYPE) {
                    return false;
                }
            } else if (!paramType.isAssignableFrom(assignableArgType(proposedArgs[i].getClass()))) {
                return false;
            }
        }
        return true;
    }

    private static Class<?> assignableArgType(Class<?> type) {
        if (type == Byte.class) {
            return Byte.TYPE;
        }
        if (type == Short.class) {
            return Short.TYPE;
        }
        if (type == Integer.class) {
            return Integer.TYPE;
        }
        if (type == Long.class) {
            return Long.TYPE;
        }
        if (type == Float.class) {
            return Float.TYPE;
        }
        if (type == Double.class) {
            return Double.TYPE;
        }
        if (type == Boolean.class) {
            return Boolean.TYPE;
        }
        if (type == Character.class) {
            return Character.TYPE;
        }
        return type;
    }

    private Method pickMethod(Class<?> klass) {
        Method[] methods;
        Class<?>[] argumentTypes = new Class[this.mMethodArgs.length];
        for (int i = 0; i < this.mMethodArgs.length; i++) {
            argumentTypes[i] = this.mMethodArgs[i].getClass();
        }
        for (Method method : klass.getMethods()) {
            String foundName = method.getName();
            Class<?>[] params = method.getParameterTypes();
            if (foundName.equals(this.mMethodName) && params.length == this.mMethodArgs.length && assignableArgType(this.mMethodResultType).isAssignableFrom(assignableArgType(method.getReturnType()))) {
                boolean assignable = true;
                for (int i2 = 0; i2 < params.length && assignable; i2++) {
                    assignable = assignableArgType(params[i2]).isAssignableFrom(assignableArgType(argumentTypes[i2]));
                }
                if (assignable) {
                    return method;
                }
            }
        }
        return null;
    }
}
