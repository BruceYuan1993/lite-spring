package com.yuanzhipeng.litespring.beans.factory.support;


import com.yuanzhipeng.litespring.beans.factory.config.RuntimeBeanReference;
import com.yuanzhipeng.litespring.beans.factory.config.TypedStringValue;

public class BeanDefinitionValueResolver {
    private final DefaultBeanFactory factory;
    public BeanDefinitionValueResolver(DefaultBeanFactory factory) {
        // TODO Auto-generated constructor stub
        this.factory = factory;
    }

    public Object resolveValueIfNecessary(Object value) {
        // TODO Auto-generated method stub
        if (value instanceof RuntimeBeanReference) {
            RuntimeBeanReference ref = (RuntimeBeanReference) value;
            String refName = ref.getBeanName();
            return this.factory.getBean(refName);
        } else if(value instanceof TypedStringValue){
            return ((TypedStringValue)value).getValue();
        } else {
            throw new RuntimeException("The value" + value + " has not implemented.");
        }
    }

}
