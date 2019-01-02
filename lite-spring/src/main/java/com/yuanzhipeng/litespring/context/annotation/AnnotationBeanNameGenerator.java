package com.yuanzhipeng.litespring.context.annotation;


import java.beans.Introspector;
import java.util.Set;

import com.yuanzhipeng.litespring.beans.BeanDefinition;
import com.yuanzhipeng.litespring.beans.factory.annotation.AnnotatedBeanDefinition;
import com.yuanzhipeng.litespring.beans.factory.support.BeanDefinitionRegistry;
import com.yuanzhipeng.litespring.beans.factory.support.BeanNameGenerator;
import com.yuanzhipeng.litespring.core.annotation.AnnotationAttributes;
import com.yuanzhipeng.litespring.core.type.AnnotationMetadata;
import com.yuanzhipeng.litespring.util.ClassUtils;
import com.yuanzhipeng.litespring.util.StringUtils;

public class AnnotationBeanNameGenerator implements BeanNameGenerator{

    @Override
    public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
        if (definition instanceof AnnotatedBeanDefinition) {
            String beanName = determineBeanNameFromAnnotation((AnnotatedBeanDefinition) definition);
            if (StringUtils.hasText(beanName)) {
                // Explicit bean name found.
                return beanName;
            }
        }
        return buildDefaultBeanName(definition, registry);
    }

    protected String determineBeanNameFromAnnotation(AnnotatedBeanDefinition annotatedDef) {
        AnnotationMetadata amd = annotatedDef.getMetadata();
        Set<String> types = amd.getAnnotationTypes();
        String beanName = null;
        for (String type : types) {
            AnnotationAttributes attributes = amd.getAnnotationAttributes(type);
            if (attributes.get("value") != null) {
                Object value = attributes.get("value");
                if (value instanceof String) {
                    String strVal = (String) value;
                    if (StringUtils.hasLength(strVal)) {                        
                        beanName = strVal;
                    }
                }
            }
        }
        return beanName;
    }


    protected String buildDefaultBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
        return buildDefaultBeanName(definition);
    }

    protected String buildDefaultBeanName(BeanDefinition definition) {
        String shortClassName = ClassUtils.getShortName(definition.getBeanClassName());
        return Introspector.decapitalize(shortClassName);
    }

}
