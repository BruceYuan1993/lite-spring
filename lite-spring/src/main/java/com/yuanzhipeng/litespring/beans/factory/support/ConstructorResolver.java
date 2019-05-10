package com.yuanzhipeng.litespring.beans.factory.support;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.yuanzhipeng.litespring.beans.BeanDefinition;
import com.yuanzhipeng.litespring.beans.ConstructorArgument;
import com.yuanzhipeng.litespring.beans.ConstructorArgument.ValueHolder;
import com.yuanzhipeng.litespring.beans.SimpleTypeConverter;
import com.yuanzhipeng.litespring.beans.factory.BeanCreationException;
import com.yuanzhipeng.litespring.beans.factory.config.ConfigurableBeanFactory;

public class ConstructorResolver {

    private final AbstractBeanFactory factory;
    public ConstructorResolver(AbstractBeanFactory factory) {
        // TODO Auto-generated constructor stub
        this.factory = factory;
    }

    public Object autowireConstructor(BeanDefinition bd) {
        // TODO Auto-generated method stub
        Constructor<?> constructorToUse = null;
        Object[] argsToUse = null;
        Class<?> beanClass = null;
        try {
            beanClass = this.factory.getBeanClassLoader().loadClass(bd.getBeanClassName());
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
           throw new BeanCreationException(bd.getId(), "Cannot load class");
        }
        
        Constructor<?>[] constructors = beanClass.getConstructors();
        ConstructorArgument cargs = bd.getConstructorArgument();
        BeanDefinitionValueResolver valueResolver = new BeanDefinitionValueResolver(factory);
        SimpleTypeConverter typeConverter = new SimpleTypeConverter();
        
        for (Constructor<?> item : constructors) {
            Class<?>[] paramTypes = item.getParameterTypes();
            if (paramTypes.length != cargs.getArgumentCount()) {
                continue;
            }
            
            argsToUse = new Object[paramTypes.length];
            
            boolean result = this.valueMatchTypes(paramTypes, cargs.getArgumentValues(),
                    argsToUse,
                    valueResolver,
                    typeConverter);
            
            if (result) {
                constructorToUse = item;
                break;
            }
        } 
        
        if (constructorToUse == null) {
            throw new BeanCreationException(bd.getId(), "Cannot find a constructor using"); 
        }
        
        try {
            return constructorToUse.newInstance(argsToUse);
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException e) {
            // TODO Auto-generated catch block
            throw new BeanCreationException(bd.getId(), "construct instance error");
        }
        
        
    }

    private boolean valueMatchTypes(Class<?>[] paramTypes, List<ValueHolder> argumentValues, Object[] argsToUse,
            BeanDefinitionValueResolver valueResolver, SimpleTypeConverter typeConverter) {
        // TODO Auto-generated method stub
        for(int i=0;i<paramTypes.length;i++){
            ConstructorArgument.ValueHolder valueHolder 
                = argumentValues.get(i);
            Object originalValue = valueHolder.getValue();
            
            try{
                Object resolvedValue = valueResolver.resolveValueIfNecessary( originalValue);
                Object convertedValue = typeConverter.convertIfNecessary(resolvedValue, paramTypes[i]);
                argsToUse[i] = convertedValue;
            }catch(Exception e){
                return false;
            }               
        }
        return true;
    }

}
