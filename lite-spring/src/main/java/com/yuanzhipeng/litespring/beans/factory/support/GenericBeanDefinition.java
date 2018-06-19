package com.yuanzhipeng.litespring.beans.factory.support;

import com.yuanzhipeng.litespring.beans.BeanDefinition;

public class GenericBeanDefinition implements BeanDefinition{

    private String id;
    private String beanClassName;
    public String getId() {
        return id;
    }
    
    public GenericBeanDefinition(String id, String beanClassName) {
        super();
        this.id = id;
        this.beanClassName = beanClassName;
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

}
