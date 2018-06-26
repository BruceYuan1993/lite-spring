package com.yuanzhipeng.litespring.beans.factory.config;

public class RuntimeBeanReference {
    private final String beanName;

    public RuntimeBeanReference(String beanName) {
        super();
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }
}
