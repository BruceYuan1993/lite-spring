package com.yuanzhipeng.litespring.beans.factory;

import com.yuanzhipeng.litespring.beans.BeanDefinition;

public interface BeanFactory {

    BeanDefinition getBeanDefinition(String beanId);

    Object getBean(String beanId);

}
