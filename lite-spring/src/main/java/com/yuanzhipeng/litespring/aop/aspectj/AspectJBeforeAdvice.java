package com.yuanzhipeng.litespring.aop.aspectj;

import com.yuanzhipeng.litespring.aop.Advice;
import com.yuanzhipeng.litespring.aop.Pointcut;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;

public class AspectJBeforeAdvice extends AbstractAspectJAdvice {
    public AspectJBeforeAdvice(Method adviceMethod, Pointcut pc, Object adviceObject) {
        super(adviceMethod, pc, adviceObject);
    }

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        invokeAdviceMethod();
        Object o = methodInvocation.proceed();
        return o;
    }
}
