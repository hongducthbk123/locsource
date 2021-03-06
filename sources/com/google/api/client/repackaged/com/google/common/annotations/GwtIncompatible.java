package com.google.api.client.repackaged.com.google.common.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.FIELD})
@GwtCompatible
@Retention(RetentionPolicy.CLASS)
public @interface GwtIncompatible {
    String value();
}
