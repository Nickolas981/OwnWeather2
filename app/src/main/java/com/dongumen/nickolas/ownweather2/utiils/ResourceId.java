package com.dongumen.nickolas.ownweather2.utiils;

import java.lang.reflect.Field;

/**
 * Created by Nickolas on 16.01.2018.
 */

public class ResourceId {

    private String resName;
    private Class<?> c;

    public ResourceId(String resName, Class<?> c) {
        this.resName = resName;
        this.c = c;
    }

    public int get(){
        try {
            Field idField = c.getDeclaredField(resName);
            return idField.getInt(idField);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
