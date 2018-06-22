package com.yuanzhipeng.litespring.beans.factory;

public interface BeanFactory {

    //BeanDefinition getBeanDefinition(String beanId);
    Object getBean(String beanId);

}
