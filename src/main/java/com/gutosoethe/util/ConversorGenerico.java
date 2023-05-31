package com.gutosoethe.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;


public class ConversorGenerico<T, U> {
    private Class<T> sourceClass;
    private Class<U> targetClass;

    public ConversorGenerico(Class<T> sourceClass, Class<U> targetClass) {
        this.sourceClass = sourceClass;
        this.targetClass = targetClass;
    }

    public List<U> convert(List<T> sourceList) {
        return sourceList.stream().map(this::convert).collect(Collectors.toList());
    }

    public U convertObject(T sourceObject) {
        try {
            Constructor<U> constructor = targetClass.getConstructor(sourceClass);
            return constructor.newInstance(sourceObject);
        } catch (Exception e) {
            e.printStackTrace();
            // Tratar adequadamente as exceções de reflexão aqui
        }

        return null;
    }


    private U convert(T sourceObject) {
        try {
            Constructor<U> constructor = targetClass.getConstructor();
            U targetObject = constructor.newInstance();

            Field[] sourceFields = sourceClass.getDeclaredFields();
            Field[] targetFields = targetClass.getDeclaredFields();

            for (Field sourceField : sourceFields) {
                for (Field targetField : targetFields) {
                    if (sourceField.getName().equals(targetField.getName())
                            && sourceField.getType().equals(targetField.getType())) {
                        sourceField.setAccessible(true);
                        targetField.setAccessible(true);
                        targetField.set(targetObject, sourceField.get(sourceObject));
                        break;
                    }
                }
            }

            return targetObject;
        } catch (Exception e) {
            e.printStackTrace();
            // Tratar adequadamente as exceções de reflexão aqui
        }

        return null;
    }
}
