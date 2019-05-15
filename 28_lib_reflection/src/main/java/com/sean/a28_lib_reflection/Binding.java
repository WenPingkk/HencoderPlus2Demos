package com.sean.a28_lib_reflection;

import android.app.Activity;

import java.lang.reflect.Field;

/**
 * Author WenPing
 * CreateTime 2019/5/15.
 * Description:
 */

public class Binding {
    public static void bind(Activity activity) {
        //反射的方式 硬编码实现
        for (Field field  :activity.getClass().getDeclaredFields() ) {
            BindView annotation = field.getAnnotation(BindView.class);
            if (annotation != null) {
                try {
                    //setAccessible 使之可见
                    field.setAccessible(true);
                    field.set(activity,activity.findViewById(annotation.value()));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}