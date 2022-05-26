package com.example.autho.annotation;

import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface MyLog {

    @AliasFor("path")
    String value() default "";

    @AliasFor("value")
    String path() default "";
    String requestUrl();

}