package com.yuanzhipeng.litespring.aop.framework;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import com.yuanzhipeng.litespring.aop.Advice;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.CallbackFilter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CglibProxyFactory implements AopProxyFactory{
    private static final int AOP_PROXY = 0;
    protected final AopConfig config;
    private Object[] constructorArgs;
    private Class<?>[] constructorArgTypes;
    
    

    public CglibProxyFactory(AopConfig config) {
        super();
        if (config.getAdvices().size() == 0) {
            throw new RuntimeException("No advisore and no targetSource specified");
        }
        this.config = config;
    }
    
    public void setConstructorArguments(Object[] constructorArgs, Class<?>[] constructorArgTypes) {
        if (constructorArgs == null || constructorArgTypes == null) {
            throw new IllegalArgumentException("Both constructorArgs and constructorArgTypes cannot be null");
        }
        if (constructorArgs.length != constructorArgTypes.length) {
            throw new IllegalArgumentException("Both constructorArgs and constructorArgTypes cannot be null");
        }
        this.constructorArgs = constructorArgs;
        this.constructorArgTypes = constructorArgTypes;
    }

    @Override
    public Object getProxy() {
        // TODO Auto-generated method stub
        return getProxy(null);
    }

    @Override
    public Object getProxy(ClassLoader classLoader) {
        // TODO Auto-generated method stub
        try {
            Class<?> rootClass = this.config.getTargetClass();
            
            Enhancer echancer = new Enhancer();
            if (classLoader != null) {
                echancer.setClassLoader(null);
            }
            echancer.setSuperclass(rootClass);
            // echancer.setNamingPolicy(SpringNameing);
            echancer.setInterceptDuringConstruction(false);
            
            Callback[] callbacks = getCallbacks(rootClass);
            Class<?>[] callbackTypes = new Class<?>[callbacks.length];
            
            for (int x = 0; x < callbackTypes.length; x++) {
                callbackTypes[x] = callbacks[x].getClass();
            }
            echancer.setCallbackFilter(new ProxyCallbackFilter(this.config));
            echancer.setCallbackTypes(callbackTypes);
            echancer.setCallbacks(callbacks);
            
            Object proxy;
            if (this.constructorArgs != null) {
                proxy = echancer.create(constructorArgTypes, constructorArgs);
            } else {
                proxy = echancer.create();
            }
            return proxy;
        } catch (Exception e) {
            throw new RuntimeException();
        }

    }
    
    private Callback[] getCallbacks(Class<?> rootClass) throws Exception {
        Callback aopInsterceptor = new DynamicInterceptor(this.config);
        Callback[] callbacks = new Callback[] {
                aopInsterceptor
        };
        
        return callbacks;
    }
    
    private static class ProxyCallbackFilter implements CallbackFilter {
        private final AopConfig config;
        
        public ProxyCallbackFilter(AopConfig config) {
            super();
            this.config = config;
        }

        @Override
        public int accept(Method arg0) {
            // TODO Auto-generated method stub
            return AOP_PROXY;
        }
    }
    
    
    private static class DynamicInterceptor implements MethodInterceptor, Serializable {
        private final AopConfig config;
        
        public DynamicInterceptor(AopConfig config) {
            super();
            this.config = config;
        }

        @Override
        public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
            // TODO Auto-generated method stub
            Class<?> targetClass = null;
            Object target = null;
            target = this.config.getTargetObject();
            if (target != null) {
                targetClass = target.getClass();
            }
            
            List<Advice> chain = this.config.getAdvices(method);
            Object retVal;
            
            if (chain.isEmpty() && Modifier.isPublic(method.getModifiers())) {
                retVal = methodProxy.invoke(target, args);
            } else {
                List<org.aopalliance.intercept.MethodInterceptor> interceptors
                = new ArrayList<>();
                interceptors.addAll(chain);
                retVal = new ReflectiveMethodInvocation(target, method, args, interceptors).proceed();
            }
            return retVal;
        }

    }
}
