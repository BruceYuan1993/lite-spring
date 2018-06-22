package com.yuanzhipeng.litespring.beans.factory.support;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.yuanzhipeng.litespring.beans.factory.config.SingletonBeanRegistry;
import com.yuanzhipeng.litespring.util.Assert;

public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry{
    private final Map<String, Object> singletonObjects = new ConcurrentHashMap<>();
    @Override
    public void registrySingleton(String beanId, Object bean) {
        // TODO Auto-generated method stub
        Assert.notNull(beanId, "beanName must not be null");
        Object oldObj = this.singletonObjects.get(beanId);
        if (oldObj != null) {
            throw new IllegalStateException("existed");
        }
        this.singletonObjects.put(beanId, bean);
    }

    @Override
    public Object getSingleton(String beanId) {
        // TODO Auto-generated method stub
        return this.singletonObjects.get(beanId);
    }

}
