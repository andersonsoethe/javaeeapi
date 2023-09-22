package com.gutosoethe.util;

import java.lang.reflect.Constructor;
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
        }

        return null;
    }

    public S convertTarget(T targetObject) {
        try {
            return (S) targetClass.getMethod("convert").invoke(targetObject);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private T convert(S sourceObject) {
        try {
            Constructor<T> constructor = targetClass.getDeclaredConstructor(sourceClass);
            T targetObject = (T) constructor.newInstance(sourceObject);
            return targetObject;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
