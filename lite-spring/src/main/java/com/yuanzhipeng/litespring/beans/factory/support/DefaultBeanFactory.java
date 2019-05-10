package com.yuanzhipeng.litespring.beans.factory.support;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.yuanzhipeng.litespring.beans.factory.NoSuchBeanDefinitionException;
import org.apache.commons.beanutils.BeanUtils;

import com.yuanzhipeng.litespring.beans.BeanDefinition;
import com.yuanzhipeng.litespring.beans.BeansException;
import com.yuanzhipeng.litespring.beans.PropertyValue;
import com.yuanzhipeng.litespring.beans.SimpleTypeConverter;
import com.yuanzhipeng.litespring.beans.TypeConverter;
import com.yuanzhipeng.litespring.beans.factory.BeanCreationException;
import com.yuanzhipeng.litespring.beans.factory.BeanFactoryAware;
import com.yuanzhipeng.litespring.beans.factory.config.BeanPostProcessor;
import com.yuanzhipeng.litespring.beans.factory.config.DependencyDescriptor;
import com.yuanzhipeng.litespring.beans.factory.config.InstantiationAwareBeanPostProcessor;
import com.yuanzhipeng.litespring.util.ClassUtils;

public class DefaultBeanFactory extends AbstractBeanFactory
        implements BeanDefinitionRegistry {
    
    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>();
    private ClassLoader beanClassLoader;
    private BeanDefinitionValueResolver resolver = new BeanDefinitionValueResolver(this);
    private TypeConverter converter = new SimpleTypeConverter();
    private List<BeanPostProcessor> postProcessors = new LinkedList<BeanPostProcessor>();
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

    @Override
    public Class<?> getType(String name) throws NoSuchBeanDefinitionException {
        BeanDefinition bd = this.getBeanDefinition(name);
        if (bd == null) {
            throw new NoSuchBeanDefinitionException(name);
        }
        resolveBeanClass(bd);
        return  bd.getBeanClass();
    }

    protected Object createBean(BeanDefinition bd) {
        // TODO Auto-generated method stub
        Object bean = instantiateBean(bd);
        populateBean(bd, bean);
        bean = initializeBean(bd, bean);
        return bean;
    }

    private void populateBean(BeanDefinition bd, Object bean) {
        // TODO Auto-generated method stub
        
        for(BeanPostProcessor processor : this.getBeanPostProcessors()) {
            if (processor instanceof InstantiationAwareBeanPostProcessor) {
                ((InstantiationAwareBeanPostProcessor) processor).postProcessPropertyValues(bean, bd.getId());
            }
        }
        
        List<PropertyValue> properties = bd.getPropertyValues();
        if (properties == null || properties.isEmpty()) {
            return;
        }

        try {
            for (PropertyValue p : properties) {
                String propertyName = p.getName();
                Object originalValue = p.getValue();
                Object resolvedValue = resolver.resolveValueIfNecessary(originalValue);
                
                BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
                PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
                for (PropertyDescriptor item : pds) {
                    if (item.getName().equals(propertyName)) {
                        Method method = item.getWriteMethod();
                        // Object resolvedValue = resolver.resolveValueIfNecessary(p.getValue());
                        method.invoke(bean, converter.convertIfNecessary(resolvedValue, item.getPropertyType()));
                        break;
                    }
                }
                
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    private void populateBeanUseCommonBeanUtils(BeanDefinition bd, Object bean) {
        List<PropertyValue> properties = bd.getPropertyValues();
        if (properties == null || properties.isEmpty()) {
            return;
        }

        try {
            for (PropertyValue p : properties) {
                String propertyName = p.getName();
                Object resolvedValue = resolver.resolveValueIfNecessary(p.getValue());
                BeanUtils.setProperty(bean, propertyName, resolvedValue);
            }
        } catch (Exception e) {
            
        }
    }

    private Object instantiateBean(BeanDefinition bd) {
        
        if(bd.hasConstructorArgumentValues()){
            ConstructorResolver resolver = new ConstructorResolver(this);
            return resolver.autowireConstructor(bd);
        }
        // TODO Auto-generated method stub
        String beanClassName = bd.getBeanClassName();
        try {
            Class<?> cls = getBeanClassLoader().loadClass(beanClassName);
            return cls.newInstance();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            throw new BeanCreationException("create bean for " + beanClassName + " failed", e);
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
        return this.beanClassLoader == null ? ClassUtils.getDefaultClassLoader() : beanClassLoader;
    }

    @Override
    public Object resolveDependency(DependencyDescriptor descriptor) {
        // TODO Auto-generated method stub
        Class<?> typeToMatch = descriptor.getDependencyType();
        for (BeanDefinition bd : beanDefinitionMap.values()) {
            resolveBeanClass(bd);
            Class<?> beanClass = bd.getBeanClass();
            if (typeToMatch.isAssignableFrom(beanClass)) {
                return this.getBean(bd.getId());
            }
        }
        return null;
    }

    private void resolveBeanClass(BeanDefinition bd) {
        // TODO Auto-generated method stub
        if (bd.hasBeanClass()) {
            return;
        } else {
            try {
                bd.resolveBeanClass(this.getBeanClassLoader());
            } catch (Exception e) {
                // TODO Auto-generated catch block
                throw new RuntimeException("Cann't load class:"+bd.getBeanClassName());
            }
        }
    }

    @Override
    public void addBeanPostProcessor(BeanPostProcessor postProcessor) {
        // TODO Auto-generated method stub
        postProcessors.add(postProcessor);
    }

    @Override
    public List<BeanPostProcessor> getBeanPostProcessors() {
        // TODO Auto-generated method stub
        return Collections.unmodifiableList(postProcessors);
    }

    @Override
    public List<Object> getBeansByType(Class<?> type){
        List<Object> result = new ArrayList<Object>();
        List<String> beanIDs = this.getBeanIDsByType(type);
        for(String beanID : beanIDs){
            result.add(this.getBean(beanID));
        }
        return result;      
    }
    
    
    protected Object initializeBean(BeanDefinition bd, Object bean)  {
        invokeAwareMethods(bean);   
        //Todo，调用Bean的init方法，暂不实现
        if(!bd.isSynthetic()){
            return applyBeanPostProcessorsAfterInitialization(bean,bd.getId());
        }
        return bean;
    }
    public Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName)
            throws BeansException {

        Object result = existingBean;
        for (BeanPostProcessor beanProcessor : getBeanPostProcessors()) {
            result = beanProcessor.afterInitialization(result, beanName);
            if (result == null) {
                return result;
            }
        }
        return result;
    }
    private void invokeAwareMethods(final Object bean) {
        if (bean instanceof BeanFactoryAware) {
            ((BeanFactoryAware) bean).setBeanFactory(this);
        }
    }
    
    private List<String> getBeanIDsByType(Class<?> type){
        List<String> result = new ArrayList<String>();
        for(String beanName :this.beanDefinitionMap.keySet()){
            if(type.isAssignableFrom(this.getType(beanName))){
                result.add(beanName);
            }
        }       
        return result;
    }
}
