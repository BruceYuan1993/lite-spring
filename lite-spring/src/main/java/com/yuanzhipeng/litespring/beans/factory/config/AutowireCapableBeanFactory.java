package com.yuanzhipeng.litespring.beans.factory.config;

import com.yuanzhipeng.litespring.beans.factory.BeanFactory;

public interface AutowireCapableBeanFactory extends BeanFactory{
    Object resolveDependency(DependencyDescriptor descriptor);
}
