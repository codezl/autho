package com.example.autho.annotation;

import java.lang.annotation.*;
 
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited()
public @interface LogAnnotation {
    String desc();
}