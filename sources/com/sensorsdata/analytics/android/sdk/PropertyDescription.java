package com.sensorsdata.analytics.android.sdk;

import p004cn.jiguang.net.HttpUtils;

class PropertyDescription {
    public final Caller accessor;
    private final String mMutatorName;
    public final String name;
    public final Class<?> targetClass;

    public PropertyDescription(String name2, Class<?> targetClass2, Caller accessor2, String mutatorName) {
        this.name = name2;
        this.targetClass = targetClass2;
        this.accessor = accessor2;
        this.mMutatorName = mutatorName;
    }

    public Caller makeMutator(Object[] methodArgs) throws NoSuchMethodException {
        if (this.mMutatorName == null) {
            return null;
        }
        return new Caller(this.targetClass, this.mMutatorName, methodArgs, Void.TYPE);
    }

    public String toString() {
        return "[PropertyDescription " + this.name + "," + this.targetClass + ", " + this.accessor + HttpUtils.PATHS_SEPARATOR + this.mMutatorName + "]";
    }
}
