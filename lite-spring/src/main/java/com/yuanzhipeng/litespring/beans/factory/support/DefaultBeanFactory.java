package com.yuanzhipeng.litespring.beans.factory.support;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.yuanzhipeng.litespring.beans.BeanDefinition;
import com.yuanzhipeng.litespring.beans.factory.BeanCreationException;
import com.yuanzhipeng.litespring.beans.factory.config.ConfigurableBeanFactory;
import com.yuanzhipeng.litespring.util.ClassUtils;

public class DefaultBeanFactory extends DefaultSingletonBeanRegistry 
                implements ConfigurableBeanFactory, BeanDefinitionRegistry {

    private static final String ATTRIBUTE_CLASS = "class";
    private static final String ATTRIBUTE_ID = "id";
    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>();
    private ClassLoader beanClassLoader;
    public BeanDefinition getBeanDefinition(String beanId) {
        // TODO Auto-generated method stub
        return beanDefinitionMap.get(beanId);
    }

    public Object getBean(String beanId) {
        // TODO Auto-generated method stub
        BeanDefinition bd = getBeanDefinition(beanId);
        if (bd == null) {
            throw new BeanCreationException("Bean Definition does not exist.");
        }
        
        if (bd.isSingleton()) {
            Object bean = this.getSingleton(beanId);
            if (bean == null) {
                bean = createBean(bd);
                this.registrySingleton(beanId, bean);
            }
            return bean;
        }
        return createBean(bd);
        
    }

    private Object createBean(BeanDefinition bd) {
        // TODO Auto-generated method stub
        String beanClassName = bd.getBeanClassName();
        try {
            Class<?> cls = getBeanClassLoader().loadClass(beanClassName);
            return cls.newInstance();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            throw new BeanCreationException("create bean for "+ beanClassName +" failed",e);
        }
    }

    @Override
    public void registryBeanDefinition(String beanId, BeanDefinition bean) {
        // TODO Auto-generated method stub
        beanDefinitionMap.put(beanId, bean);
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        // TODO Auto-generated method stub
        beanClassLoader = classLoader;
    }

    @Override
    public ClassLoader getBeanClassLoader() {
        // TODO Auto-generated method stub
        return this.beanClassLoader == null ? ClassUtils.getDefaultClassLoader(): beanClassLoader;
    }

}
