package com.yuanzhipeng.litespring.beans.factory.xml;

import java.io.InputStream;
import java.util.Iterator;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.yuanzhipeng.litespring.beans.BeanDefinition;
import com.yuanzhipeng.litespring.beans.ConstructorArgument.ValueHolder;
import com.yuanzhipeng.litespring.beans.PropertyValue;
import com.yuanzhipeng.litespring.beans.factory.BeanDefinitionStoreException;
import com.yuanzhipeng.litespring.beans.factory.config.RuntimeBeanReference;
import com.yuanzhipeng.litespring.beans.factory.config.TypedStringValue;
import com.yuanzhipeng.litespring.beans.factory.support.BeanDefinitionRegistry;
import com.yuanzhipeng.litespring.beans.factory.support.GenericBeanDefinition;
import com.yuanzhipeng.litespring.context.annotation.ClassPathBeanDefinitionScanner;
import com.yuanzhipeng.litespring.core.io.Resource;
import com.yuanzhipeng.litespring.util.StringUtils;

public class XmlBeanDefinitionReader {
    private static final String ATTRIBUTE_CLASS = "class";
    private static final String ATTRIBUTE_ID = "id";
    private static final String ATTRIBUTE_SCOPE = "scope";
    
    private static final String ATTRIBUTE_PROPERTY = "property";
    private static final String ATTRIBUTE_REF = "ref";
    private static final String ATTRIBUTE_VALUE = "value";
    private static final String ATTRIBUTE_NAME = "name";
    
    private static final String ATTRIBUTE_CONSTRUCTOR_ARG = "constructor-arg";
    private static final String ATTRIBUTE_TYPE = "type";
    
    public static final String BEANS_NAMESPACE_URI = "http://www.springframework.org/schema/beans";

    public static final String CONTEXT_NAMESPACE_URI = "http://www.springframework.org/schema/context";
    
    private static final String BASE_PACKAGE_ATTRIBUTE = "base-package";
    
    private static final Logger logger = LogManager.getLogger(XmlBeanDefinitionReader.class);;
    
    private BeanDefinitionRegistry registry;

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super();
        this.registry = registry;
    }

    public void loadBeanDefinition(Resource resource) {
        //ʹ��try-with-resource���ر�InputStream
        try (InputStream in = resource.getInputStream()){
            SAXReader reader = new SAXReader();
            Document doc = reader.read(in);
            Element root = doc.getRootElement();
            Iterator<?> iter = root.elementIterator();
            //����ط��Ǽٶ������ӽڵ㶼��bean�ڵ�
            while (iter.hasNext()) {
                Element ele = (Element)iter.next();
                
                String namespaceUri = ele.getNamespaceURI();
                if(this.isDefaultNamespace(namespaceUri)){
                    parseDefaultElement(ele); //普通的bean
                } else if(this.isContextNamespace(namespaceUri)){
                    parseComponentElement(ele); //例如<context:component-scan>
                } 
                
                
            }
        } catch (Exception e) {
            throw new BeanDefinitionStoreException("IOException parsing xml");
        }
    }
    
    
    private void parseComponentElement(Element ele) {
        String basePackages = ele.attributeValue(BASE_PACKAGE_ATTRIBUTE);
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(registry);
        scanner.doScan(basePackages);       
        
    }
    private void parseDefaultElement(Element ele) {
        String id = ele.attributeValue(ATTRIBUTE_ID);
        String beanClassName = ele.attributeValue(ATTRIBUTE_CLASS);
        BeanDefinition bd = new GenericBeanDefinition(id, beanClassName);
        if (ele.attributeValue(ATTRIBUTE_SCOPE) != null) {
            bd.setScope(ele.attributeValue(ATTRIBUTE_SCOPE));
        }
        parseConstructorArgumentElement(ele, bd);
        parsePropertyElement(ele, bd);
        registry.registryBeanDefinition(id, bd); 
    }
    

    private void parseConstructorArgumentElement(Element beanEle, BeanDefinition bd) {
        // TODO Auto-generated method stub
        Iterator<?> iter = beanEle.elementIterator(ATTRIBUTE_CONSTRUCTOR_ARG);
        while (iter.hasNext()) {
            Element ele = (Element)iter.next();
            parseConstructorArgumentValue(ele, bd);
        }
    }

    private void parseConstructorArgumentValue(Element ele, BeanDefinition bd) {
        // TODO Auto-generated method stub
        String name = ele.attributeValue(ATTRIBUTE_NAME);
        String type = ele.attributeValue(ATTRIBUTE_TYPE);
        
        Object val = parsePropertyValue(ele, bd, null);
        ValueHolder holder = new ValueHolder(val);
        
        if (StringUtils.hasLength(name)) {
            holder.setName(name);
        }
        
        if (StringUtils.hasLength(type)) {
            holder.setType(type);
        }
        bd.getConstructorArgument().addArgumentValue(holder);
    }

    private void parsePropertyElement(Element beanEle, BeanDefinition bd) {
        // TODO Auto-generated method stub
        Iterator<?> iter = beanEle.elementIterator(ATTRIBUTE_PROPERTY);
        while (iter.hasNext()) {
            Element ele = (Element)iter.next();
            String name = ele.attributeValue(ATTRIBUTE_NAME);
            if (!StringUtils.hasLength(name)) {
                logger.fatal("Tag 'property' must have a 'name' attribute.");
                return;
            }
            Object val = parsePropertyValue(ele, bd, name);
            PropertyValue pv = new PropertyValue(name, val);
            bd.getPropertyValues().add(pv);
        }
    }

    private Object parsePropertyValue(Element ele, BeanDefinition bd, String name) {
        boolean hasRefAttribute = (ele.attributeValue(ATTRIBUTE_REF)!= null);
        boolean hasValAttribute = (ele.attributeValue(ATTRIBUTE_VALUE)!= null);
        if (hasRefAttribute) {
            String refName = ele.attributeValue(ATTRIBUTE_REF);
            return new RuntimeBeanReference(refName);
        } else if (hasValAttribute){
            String value = ele.attributeValue(ATTRIBUTE_VALUE);
            return new TypedStringValue(value);
        } else {
            throw new RuntimeException(name + " must specify a ref or value");
        }
    }
    
    
    
    public boolean isDefaultNamespace(String namespaceUri) {
        return (!StringUtils.hasLength(namespaceUri) || BEANS_NAMESPACE_URI.equals(namespaceUri));
    }
    public boolean isContextNamespace(String namespaceUri){
        return (!StringUtils.hasLength(namespaceUri) || CONTEXT_NAMESPACE_URI.equals(namespaceUri));
    }
    
}
