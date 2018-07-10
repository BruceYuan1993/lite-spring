package com.yuanzhipeng.litespring.beans;

import java.util.List;


public interface BeanDefinition {

    String getBeanClassName();
    
    public static final String SCOPE_SINGLETON = "singleton";
    public static final String SCOPE_PROTOTYPE = "prototype";
    public static final String SCOPE_DEFAULT = "";
    
    public boolean isSingleton();
    public boolean isPrototype();
    String getId();
    String getScope();
    void setScope(String scope);
    List<PropertyValue> getPropertyValues();
    ConstructorArgument getConstructorArgument();
    boolean hasConstructorArgumentValues();
}
