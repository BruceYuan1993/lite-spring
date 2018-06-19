package com.yuanzhipeng.litespring.beans.factory.support;

import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.yuanzhipeng.litespring.beans.BeanDefinition;
import com.yuanzhipeng.litespring.beans.factory.BeanCreationException;
import com.yuanzhipeng.litespring.beans.factory.BeanDefinitionStoreException;
import com.yuanzhipeng.litespring.beans.factory.BeanFactory;
import com.yuanzhipeng.litespring.util.ClassUtils;

public class DefaultBeanFactory implements BeanFactory {

    private static final String ATTRIBUTE_CLASS = "class";
    private static final String ATTRIBUTE_ID = "id";
    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>();

    public DefaultBeanFactory(String configFile) {
        loadBeanDefinition(configFile);
    }

    private void loadBeanDefinition(String configFile) {
        ClassLoader cl = ClassUtils.getDefaultClassLoader();
        //使用try-with-resource来关闭InputStream
        try (InputStream in = cl.getResourceAsStream(configFile)){
            SAXReader reader = new SAXReader();
            Document doc = reader.read(in);
            Element root = doc.getRootElement();
            Iterator<?> iter = root.elementIterator();
            //这个地方是假定所有子节点都是bean节点
            while (iter.hasNext()) {
                Element ele = (Element)iter.next();
                String id = ele.attributeValue(ATTRIBUTE_ID);
                String beanClassName = ele.attributeValue(ATTRIBUTE_CLASS);
                BeanDefinition db = new GenericBeanDefinition(id, beanClassName);
                beanDefinitionMap.put(id, db);
            }
        } catch (Exception e) {
            throw new BeanDefinitionStoreException("IOException parsing xml");
        }
    }

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
        String beanClassName = bd.getBeanClassName();
        try {
            Class<?> cls = ClassUtils.getDefaultClassLoader().loadClass(beanClassName);
            return cls.newInstance();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            throw new BeanCreationException("create bean for "+ beanClassName +" failed",e);
        }
    }

}
