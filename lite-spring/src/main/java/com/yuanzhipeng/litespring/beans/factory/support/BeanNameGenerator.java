package com.yuanzhipeng.litespring.beans.factory.support;

import com.yuanzhipeng.litespring.beans.BeanDefinition;

public interface BeanNameGenerator {
    String generateBeanName(BeanDefinition bd, BeanDefinitionRegistry registry);
}
