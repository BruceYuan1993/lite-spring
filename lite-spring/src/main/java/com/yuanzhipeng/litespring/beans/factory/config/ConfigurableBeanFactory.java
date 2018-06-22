package com.yuanzhipeng.litespring.beans.factory.config;

import com.yuanzhipeng.litespring.beans.factory.BeanFactory;

public interface ConfigurableBeanFactory extends BeanFactory{
    void setBeanClassLoader(ClassLoader classLoader);
    ClassLoader getBeanClassLoader();
}
