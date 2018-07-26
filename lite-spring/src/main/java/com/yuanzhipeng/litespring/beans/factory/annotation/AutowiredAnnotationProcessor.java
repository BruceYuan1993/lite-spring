package com.yuanzhipeng.litespring.beans.factory.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

import com.yuanzhipeng.litespring.beans.BeansException;
import com.yuanzhipeng.litespring.beans.factory.config.AutowireCapableBeanFactory;
import com.yuanzhipeng.litespring.beans.factory.config.InstantiationAwareBeanPostProcessor;
import com.yuanzhipeng.litespring.util.AnnotationUtils;
import com.yuanzhipeng.litespring.util.ReflectionUtils;

public class AutowiredAnnotationProcessor implements InstantiationAwareBeanPostProcessor{
    private AutowireCapableBeanFactory beanFactory;
    private String requiredParameterName = "required";
    private boolean requiredParameterValue = true;
    
    private final Set<Class<? extends Annotation>> autowiredAnnotationTypes = new LinkedHashSet<>();

    public AutowiredAnnotationProcessor() {
        this.autowiredAnnotationTypes.add(Autowired.class);
    }

    public InjectionMetadata buildAutowiringMetadata(Class<?> clz) {
        // TODO Auto-generated method stub
        LinkedList<InjectionElement> elements = new LinkedList<>();
        Class<?> targetClass = clz;
        do {
            LinkedList<InjectionElement> currElements = new LinkedList<>();
            for (Field field : clz.getDeclaredFields()) {
                Annotation ann = findAutowiredAnnotation(field);
                if (ann != null) {
                    if (Modifier.isStatic(field.getModifiers())) {
                        continue;
                    }
                    boolean required = deteminRequiredStatus(ann);
                    currElements.add(new AutowiredFieldElement(field, required, beanFactory));
                }
            }
            //method
            
            elements.addAll(0, currElements);
            targetClass = targetClass.getSuperclass();
        } while(targetClass != null && targetClass != Object.class);
        return new InjectionMetadata(clz, elements);
    }

    public AutowireCapableBeanFactory getBeanFactory() {
        return beanFactory;
    }

    public void setBeanFactory(AutowireCapableBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    private boolean deteminRequiredStatus(Annotation ann) {
        // TODO Auto-generated method stub
        Method method = ReflectionUtils.findMethod(ann.annotationType(), this.requiredParameterName);
        if (method == null) {
            return true;
        }
        return this.requiredParameterValue == (boolean)ReflectionUtils.invokeMethod(method, ann);
    }

    private Annotation findAutowiredAnnotation(AccessibleObject ao) {
        // TODO Auto-generated method stub
        for (Class<? extends Annotation> type : this.autowiredAnnotationTypes) {
            Annotation ann = AnnotationUtils.getAnnotation(ao, type);
            if (ann != null) {
                return ann;
            }
        }
        return null;
    }

    @Override
    public Object beforeInitialization(Object bean, String beanName) throws BeansException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object afterInitialization(Object bean, String beanName) throws BeansException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object beforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean afterInstantiation(Object bean, String beanName) throws BeansException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void postProcessPropertyValues(Object bean, String beanName) throws BeansException {
        // TODO Auto-generated method stub
        InjectionMetadata metadata = buildAutowiringMetadata(bean.getClass());
        metadata.inject(bean);
    }
    
}
