package com.yuanzhipeng.litespring.beans.factory.xml;

import java.io.InputStream;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.yuanzhipeng.litespring.beans.BeanDefinition;
import com.yuanzhipeng.litespring.beans.factory.BeanDefinitionStoreException;
import com.yuanzhipeng.litespring.beans.factory.support.BeanDefinitionRegistry;
import com.yuanzhipeng.litespring.beans.factory.support.GenericBeanDefinition;
import com.yuanzhipeng.litespring.core.io.Resource;
import com.yuanzhipeng.litespring.util.ClassUtils;

public class XmlBeanDefinitionReader {
    private static final String ATTRIBUTE_CLASS = "class";
    private static final String ATTRIBUTE_ID = "id";
    private static final String ATTRIBUTE_SCOPE = "scope";
    private BeanDefinitionRegistry registry;

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super();
        this.registry = registry;
    }

    public void loadBeanDefinition(Resource resource) {
        ClassLoader cl = ClassUtils.getDefaultClassLoader();
        //ʹ��try-with-resource���ر�InputStream
        try (InputStream in = resource.getInputStream()){
            SAXReader reader = new SAXReader();
            Document doc = reader.read(in);
            Element root = doc.getRootElement();
            Iterator<?> iter = root.elementIterator();
            //����ط��Ǽٶ������ӽڵ㶼��bean�ڵ�
            while (iter.hasNext()) {
                Element ele = (Element)iter.next();
                String id = ele.attributeValue(ATTRIBUTE_ID);
                String beanClassName = ele.attributeValue(ATTRIBUTE_CLASS);
                BeanDefinition bd = new GenericBeanDefinition(id, beanClassName);
                if (ele.attributeValue(ATTRIBUTE_SCOPE) != null) {
                    bd.setScope(ele.attributeValue(ATTRIBUTE_SCOPE));
                }
                
                registry.registryBeanDefinition(id, bd);
            }
        } catch (Exception e) {
            throw new BeanDefinitionStoreException("IOException parsing xml");
        }
    }
}
