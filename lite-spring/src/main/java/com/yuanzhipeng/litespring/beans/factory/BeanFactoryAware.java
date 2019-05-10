package com.yuanzhipeng.litespring.beans.factory;

import com.yuanzhipeng.litespring.beans.BeansException;

public interface BeanFactoryAware {
    void setBeanFactory(BeanFactory beanFactory) throws BeansException;
}
