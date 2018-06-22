package com.yuanzhipeng.litespring.beans.factory.support;

import com.yuanzhipeng.litespring.beans.BeanDefinition;

public interface BeanDefinitionRegistry {
    public BeanDefinition getBeanDefinition(String beanId);
    public void registryBeanDefinition(String beanId, BeanDefinition bean);
}
