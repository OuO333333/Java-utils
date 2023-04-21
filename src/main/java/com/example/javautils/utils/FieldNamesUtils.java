package com.example.javautils.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class FieldNamesUtils {
    public static ArrayList<String> getFieldNames(Class<?> classzz, String annotationName) {
        ArrayList<String> fieldNamesArr = new ArrayList<String>();
        Field[] fields = classzz.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields[i].getAnnotations().length; j++) {
                if (fields[i].getAnnotations()[j].annotationType().getSimpleName().compareTo(annotationName) == 0) {
                    fieldNamesArr.add(fields[i].getName());
                }
            }
        }
        return fieldNamesArr;
    }
}
