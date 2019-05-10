package com.yuanzhipeng.litespring.beans.factory.config;

import com.yuanzhipeng.litespring.beans.BeansException;
import com.yuanzhipeng.litespring.beans.factory.BeanFactory;
import com.yuanzhipeng.litespring.beans.factory.BeanFactoryAware;
import com.yuanzhipeng.litespring.util.StringUtils;

public class AspectInstanceFactory implements BeanFactoryAware{
    private String aspectBeanName;
    private BeanFactory beanFactory;
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        // TODO Auto-generated method stub
        this.beanFactory = beanFactory;
        if (!StringUtils.hasText(this.aspectBeanName)) {
            throw new IllegalArgumentException("aspectBeanName is required");
        }
        
    }
    public void setAspectBeanName(String aspectBeanName) {
        this.aspectBeanName = aspectBeanName;
    }
    
    public Object getAspectInstance() throws Exception {
        return this.beanFactory.getBean(aspectBeanName);
    }

}
