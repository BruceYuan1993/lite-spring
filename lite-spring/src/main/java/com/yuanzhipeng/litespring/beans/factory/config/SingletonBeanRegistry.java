package com.yuanzhipeng.litespring.beans.factory.config;


public interface SingletonBeanRegistry {
    void registrySingleton(String beanId, Object bean);
    Object getSingleton(String beanId);
}
