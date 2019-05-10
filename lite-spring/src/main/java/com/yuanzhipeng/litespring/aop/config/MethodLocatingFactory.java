package com.yuanzhipeng.litespring.aop.config;

import com.yuanzhipeng.litespring.beans.BeanUtils;
import com.yuanzhipeng.litespring.beans.factory.BeanFactory;
import com.yuanzhipeng.litespring.beans.factory.BeanFactoryAware;
import com.yuanzhipeng.litespring.beans.factory.FactoryBean;
import com.yuanzhipeng.litespring.util.StringUtils;

import java.lang.reflect.Method;

public class MethodLocatingFactory implements FactoryBean<Method>, BeanFactoryAware{
    private String targetBeanName;
    private String methodName;
    private Method method;

    public void setTargetBeanName(String targetBeanName){
        this.targetBeanName = targetBeanName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public void setBeanFactory(BeanFactory beanFactory){
        if (!StringUtils.hasText(this.targetBeanName)) {
            throw  new IllegalArgumentException("BeanName is required");
        }

        if (!StringUtils.hasText(this.methodName)) {
            throw  new IllegalArgumentException("methodName is required");
        }

        Class<?> beanClass = beanFactory.getType(this.targetBeanName);
        if (beanClass == null) {
            throw new IllegalArgumentException("Cannot determine type of bean with name " + this.targetBeanName);
        }

        this.method = BeanUtils.resolveSignature(methodName, beanClass);
        if (method == null) {
            throw new IllegalArgumentException("unable to locate method "+ methodName +"of bean " + this.targetBeanName);
        }
    }

    public  Method getObject() throws Exception{
        return this.method;
    }

    @Override
    public Class<?> getObjectType() {
        // TODO Auto-generated method stub
        return Method.class;
    }
}
