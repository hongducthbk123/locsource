package org.cocos2dx.lib;

import android.util.Log;
import java.lang.reflect.InvocationTargetException;

public class Cocos2dxReflectionHelper {
    public static <T> T getConstantValue(Class aClass, String constantName) {
        boolean z = false;
        try {
            return aClass.getDeclaredField(constantName).get(null);
        } catch (NoSuchFieldException e) {
            Log.e("error", "can not find " + constantName + " in " + aClass.getName());
            return z;
        } catch (IllegalAccessException e2) {
            Log.e("error", constantName + " is not accessible");
            return z;
        } catch (IllegalArgumentException e3) {
            Log.e("error", "arguments error when get " + constantName);
            return z;
        } catch (Exception e4) {
            Log.e("error", "can not get constant" + constantName);
            return z;
        }
    }

    public static <T> T invokeInstanceMethod(Object instance, String methodName, Class[] parameterTypes, Object[] parameters) {
        Class aClass = instance.getClass();
        try {
            return aClass.getMethod(methodName, parameterTypes).invoke(instance, parameters);
        } catch (NoSuchMethodException e) {
            Log.e("error", "can not find " + methodName + " in " + aClass.getName());
        } catch (IllegalAccessException e2) {
            Log.e("error", methodName + " is not accessible");
        } catch (IllegalArgumentException e3) {
            Log.e("error", "arguments are error when invoking " + methodName);
        } catch (InvocationTargetException e4) {
            Log.e("error", "an exception was thrown by the invoked method when invoking " + methodName);
        }
        return null;
    }
}
