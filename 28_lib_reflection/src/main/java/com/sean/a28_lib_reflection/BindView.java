package com.sean.a28_lib_reflection;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Author WenPing
 * CreateTime 2019/5/15.
 * Description:
 * Retention 注解的保留等级 SOURCE CLASS RUNTIME
 * Target 修饰类型 METHOD FIELD
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface BindView {
    int value();
}
