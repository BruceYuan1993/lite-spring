package com.yuanzhipeng.litespring.aop.aspectj;

import com.yuanzhipeng.litespring.aop.Pointcut;
import com.yuanzhipeng.litespring.beans.factory.config.AspectInstanceFactory;

import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;

public class AspectJBeforeAdvice extends AbstractAspectJAdvice {
    public AspectJBeforeAdvice(Method adviceMethod, Pointcut pc, AspectInstanceFactory adviceObjectFactory) {
        super(adviceMethod, pc, adviceObjectFactory);
    }

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        invokeAdviceMethod();
        Object o = methodInvocation.proceed();
        return o;
    }
}
