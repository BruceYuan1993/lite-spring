package com.yuanzhipeng.litespring.beans.factory.annotation;

import com.yuanzhipeng.litespring.beans.BeanDefinition;
import com.yuanzhipeng.litespring.core.type.AnnotationMetadata;

public interface AnnotatedBeanDefinition extends BeanDefinition{
    AnnotationMetadata getMetadata();
}
