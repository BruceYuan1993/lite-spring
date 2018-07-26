package com.yuanzhipeng.litespring.beans.factory.support;

import java.util.ArrayList;
import java.util.List;

import com.yuanzhipeng.litespring.beans.BeanDefinition;
import com.yuanzhipeng.litespring.beans.ConstructorArgument;
import com.yuanzhipeng.litespring.beans.PropertyValue;

public class GenericBeanDefinition implements BeanDefinition{

    private String id;
    private String beanClassName;
    private String scope = "";
    private Class<?> beanClass;
    private List<PropertyValue> pvs = new ArrayList<>();
    private ConstructorArgument constructorArgument = new ConstructorArgument();
    
    
    public String getId() {
        return id;
    }
    
    public GenericBeanDefinition(String id, String beanClassName) {
        super();
        this.id = id;
        this.beanClassName = beanClassName;
    }
    public GenericBeanDefinition() {
        // TODO Auto-generated constructor stub
    }

    public void setBeanClassName(String beanClassName) {
        this.beanClassName = beanClassName;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getBeanClassName() {
        // TODO Auto-generated method stub
        return beanClassName;
    }

    @Override
    public boolean isSingleton() {
        // TODO Auto-generated method stub
        return scope.equalsIgnoreCase(SCOPE_SINGLETON)||scope.equalsIgnoreCase(SCOPE_DEFAULT);
    }

    @Override
    public boolean isPrototype() {
        // TODO Auto-generated method stub
        return scope.equalsIgnoreCase(SCOPE_PROTOTYPE);
    }

    @Override
    public String getScope() {
        // TODO Auto-generated method stub
        return scope;
    }

    @Override
    public void setScope(String scope) {
        // TODO Auto-generated method stub
        this.scope = scope;
    }

    @Override
    public List<PropertyValue> getPropertyValues() {
        // TODO Auto-generated method stub
        return pvs;
    }

    @Override
    public ConstructorArgument getConstructorArgument() {
        // TODO Auto-generated method stub
        return constructorArgument;
    }

    @Override
    public boolean hasConstructorArgumentValues() {
        // TODO Auto-generated method stub
        return !this.constructorArgument.isEmpty();
    }

    @Override
    public boolean hasBeanClass() {
        // TODO Auto-generated method stub
        return beanClass != null;
    }

    @Override
    public Class<?> resolveBeanClass(ClassLoader beanClassLoader) throws Exception {
        // TODO Auto-generated method stub
        if (beanClassName == null) {
            return null;
        }
        Class<?> resolvedClass = beanClassLoader.loadClass(beanClassName);
        this.beanClass = resolvedClass;
        return resolvedClass;
    }

    @Override
    public Class<?> getBeanClass() {
        // TODO Auto-generated method stub
        if (this.beanClass == null) {
            throw new IllegalStateException("Bean class name [" + this.beanClassName + "] has not been resolved into");
        }
        return this.beanClass;
    }

}
