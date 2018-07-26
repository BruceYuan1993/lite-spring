package com.yuanzhipeng.litespring.context.annotation;

import com.yuanzhipeng.litespring.beans.factory.annotation.AnnotatedBeanDefinition;
import com.yuanzhipeng.litespring.beans.factory.support.GenericBeanDefinition;
import com.yuanzhipeng.litespring.core.type.AnnotationMetadata;

public class ScannedGenericBeanDefinition extends GenericBeanDefinition implements AnnotatedBeanDefinition{
    private final AnnotationMetadata metadata;
    public ScannedGenericBeanDefinition(AnnotationMetadata metadata) {
        super();
        // TODO Auto-generated constructor stub
        this.metadata = metadata;
        setBeanClassName(this.metadata.getClassName());
    }
    public final AnnotationMetadata getMetadata() {
        return metadata;
    }
}
