package com.yuanzhipeng.litespring.context.support;


import com.yuanzhipeng.litespring.beans.factory.support.DefaultBeanFactory;
import com.yuanzhipeng.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import com.yuanzhipeng.litespring.context.ApplicationContext;
import com.yuanzhipeng.litespring.core.io.Resource;
import com.yuanzhipeng.litespring.util.ClassUtils;

public abstract class AbstractApplicationContext implements ApplicationContext{
    private DefaultBeanFactory factory;
    private ClassLoader beanClassLoader;
    public AbstractApplicationContext(String configFile) {
        // TODO Auto-generated constructor stub
        factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinition(getResourceByPath(configFile));
        factory.setBeanClassLoader(getBeanClassLoader());
    }

    @Override
    public Object getBean(String beanId) {
        // TODO Auto-generated method stub
        return factory.getBean(beanId);
    }
    
    protected abstract Resource getResourceByPath(String path);
    
//    @Override
//    public void setBeanClassLoader(ClassLoader classLoader) {
//        // TODO Auto-generated method stub
//        factory.setBeanClassLoader(classLoader);
//    }
//
//    @Override
//    public ClassLoader getBeanClassLoader() {
//        // TODO Auto-generated method stub
//        return factory.getBeanClassLoader();
//    }
    
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
