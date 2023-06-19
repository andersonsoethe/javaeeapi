package com.gutosoethe.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;


public class ConversorGenerico<S, T> {
    private Class<S> sourceClass;
    private Class<T> targetClass;

    public ConversorGenerico(Class<S> sourceClass, Class<T> targetClass) {
        this.sourceClass = sourceClass;
        this.targetClass = targetClass;
    }

    public List<T> convert(List<S> sourceList) {
        return sourceList.stream().map(this::convert).collect(Collectors.toList());
    }

    public T convertSource(S sourceObject) {
        try {
            Constructor<T> constructor = targetClass.getConstructor(sourceClass);
            return constructor.newInstance(sourceObject);
        } catch (Exception e) {
            e.printStackTrace();
            // Tratar adequadamente as exceções de reflexão aqui
        }

        return null;
    }

    public S convertTarget(T targetObject) {
        try {
            return (S) targetClass.getMethod("convert").invoke(targetObject);
        } catch (Exception e) {
            e.printStackTrace();
            // Tratar adequadamente as exceções de reflexão aqui
        }

        return null;
    }


    private T convert(S sourceObject) {
        try {
            Constructor<T> constructor = targetClass.getConstructor();
            T targetObject = constructor.newInstance();

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
