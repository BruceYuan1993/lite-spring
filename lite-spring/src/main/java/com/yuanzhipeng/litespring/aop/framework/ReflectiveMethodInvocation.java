package com.yuanzhipeng.litespring.aop.framework;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class ReflectiveMethodInvocation implements MethodInvocation {
    protected final Object targetObject;
    protected final Method tergetMethod;
    protected Object[] arguments;

    private final List<MethodInterceptor> interceptors;

    private int currentInterceptorindex = -1;

    public ReflectiveMethodInvocation(Object targetObject, Method tergetMethod, Object[] arguments, List<MethodInterceptor> interceptors) {
        this.targetObject = targetObject;
        this.tergetMethod = tergetMethod;
        this.arguments = arguments;
        this.interceptors = interceptors;
    }

    @Override
    public final Method getMethod() {
        return this.tergetMethod;
    }

    @Override
    public final Object[] getArguments() {
        return this.arguments != null ? this.arguments : new Object[0];
    }

    @Override
    public Object proceed() throws Throwable {
       if (this.currentInterceptorindex == this.interceptors.size() - 1){
           return invokeJoinpoint();
       }
       this.currentInterceptorindex++;
       MethodInterceptor interceptor = this.interceptors.get(currentInterceptorindex);

       return interceptor.invoke(this);
    }

    private Object invokeJoinpoint() throws Exception {
        return this.tergetMethod.invoke(targetObject, arguments);
    }

    @Override
    public final Object getThis() {
        return this.targetObject;
    }

    @Override
    public AccessibleObject getStaticPart() {
        return this.tergetMethod;
    }
}
