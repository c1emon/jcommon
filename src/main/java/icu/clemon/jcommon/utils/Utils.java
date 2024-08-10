package icu.clemon.jcommon.utils;

import org.apache.commons.lang3.ClassUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Utils {
    public static void getFields(List<Field> fields, Class<?> clazz) {

        fields.addAll(Arrays.asList(clazz.getDeclaredFields()));

        Class<?> superClazz = clazz.getSuperclass();
        if (superClazz != null) {
            getFields(fields, superClazz);
        }
    }

    public static List<Field> getFieldsDeep(Class<?> clazz) {
        List<Field> fields = new LinkedList<>();
        getFields(fields, clazz);
        return fields;
    }

    public static <T> T merge(T local, T remote) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class<?> clazz = local.getClass();
        Object merged = clazz.getDeclaredConstructor().newInstance();

        for (Field field : getFieldsDeep(clazz)) {
            field.setAccessible(true);
            Object remoteField = field.get(remote);

            if (remoteField != null) {
                Class<?> clz = remoteField.getClass();
                if (ClassUtils.isPrimitiveOrWrapper(clz) || clz.isAssignableFrom(String.class)) {
                    field.set(merged, remoteField);
                } else {
                    Object localField = field.get(local);
                    field.set(merged, localField != null ? merge(localField, remoteField) : remoteField);
                }
            }
        }
        //noinspection unchecked
        return (T) merged;
    }
}
