package icu.clemon.common.http;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ResponseWrapper {
    @AliasFor("value")
    boolean enabled() default true;
    @AliasFor("enabled")
    boolean value() default true;
}