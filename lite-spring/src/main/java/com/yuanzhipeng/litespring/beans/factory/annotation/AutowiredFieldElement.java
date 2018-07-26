package com.yuanzhipeng.litespring.beans.factory.annotation;

import java.lang.reflect.Field;

import com.yuanzhipeng.litespring.beans.factory.BeanCreationException;
import com.yuanzhipeng.litespring.beans.factory.config.AutowireCapableBeanFactory;
import com.yuanzhipeng.litespring.beans.factory.config.DependencyDescriptor;
import com.yuanzhipeng.litespring.util.ReflectionUtils;

public class AutowiredFieldElement extends InjectionElement {
    private boolean required;
    
    public AutowiredFieldElement(Field field, boolean required, AutowireCapableBeanFactory factory) {
        super(field, factory);
        this.required = required;
        // TODO Auto-generated constructor stub
    }
    
    public Field getField() {
        return (Field) member;
    }

    @Override
    public void inject(Object target) {
        // TODO Auto-generated method stub
        Field field = getField();
        
        
        DependencyDescriptor desc = new DependencyDescriptor(field, required);
        Object value = factory.resolveDependency(desc);
        if (value != null) {
            try {
                ReflectionUtils.makeAccessible(field);
                field.set(target, value);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                throw new BeanCreationException("Cannot autowired field: " + field, e);
            }
        }
    }

}
