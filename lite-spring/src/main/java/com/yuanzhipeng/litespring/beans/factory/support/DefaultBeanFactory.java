package com.yuanzhipeng.litespring.beans.factory.support;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.beanutils.BeanUtils;

import com.yuanzhipeng.litespring.beans.BeanDefinition;
import com.yuanzhipeng.litespring.beans.PropertyValue;
import com.yuanzhipeng.litespring.beans.SimpleTypeConverter;
import com.yuanzhipeng.litespring.beans.TypeConverter;
import com.yuanzhipeng.litespring.beans.factory.BeanCreationException;
import com.yuanzhipeng.litespring.beans.factory.config.ConfigurableBeanFactory;
import com.yuanzhipeng.litespring.util.ClassUtils;

public class DefaultBeanFactory extends DefaultSingletonBeanRegistry
        implements ConfigurableBeanFactory, BeanDefinitionRegistry {

    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>();
    private ClassLoader beanClassLoader;
    private BeanDefinitionValueResolver resolver = new BeanDefinitionValueResolver(this);
    private TypeConverter converter = new SimpleTypeConverter();
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
        Object bean = instantiateBean(bd);
        populateBeanUseCommonBeanUtils(bd, bean);
        return bean;
    }

    private void populateBean(BeanDefinition bd, Object bean) {
        // TODO Auto-generated method stub
        List<PropertyValue> properties = bd.getPropertyValues();
        if (properties == null || properties.isEmpty()) {
            return;
        }

        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
            PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
            for (PropertyValue p : properties) {
                for (PropertyDescriptor item : pds) {
                    if (item.getName().equals(p.getName())) {
                        Method method = item.getWriteMethod();
                        Object resolvedValue = resolver.resolveValueIfNecessary(p.getValue());
                        method.invoke(bean, converter.convertIfNecessary(resolvedValue, item.getPropertyType()));
                        break;
                    }
                }
                
            }
        } catch (Exception e) {
            
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

}
