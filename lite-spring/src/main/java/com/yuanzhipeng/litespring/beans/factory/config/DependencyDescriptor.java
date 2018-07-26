package com.yuanzhipeng.litespring.beans.factory.config;

import java.lang.reflect.Field;

public class DependencyDescriptor {
    private final Field field;
    
    private boolean required;
    
    public boolean isRequired() {
        return required;
    }
    public DependencyDescriptor(Field field, boolean required) {
        super();
        this.field = field;
        this.required = required;
    }
    
    public Class<?> getDependencyType(){
        if (field != null) {
            return field.getType();
        }
        throw new RuntimeException("Only support field dependency");
    }
}
