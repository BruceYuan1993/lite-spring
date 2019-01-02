package com.yuanzhipeng.litespring.aop.aspectj;

import com.yuanzhipeng.litespring.aop.Advice;
import com.yuanzhipeng.litespring.aop.Pointcut;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;

public class AspectJAfterReturningAdvice extends AbstractAspectJAdvice {

    public AspectJAfterReturningAdvice(Method adviceMethod, Pointcut pc, Object adviceObject) {
        super(adviceMethod, pc, adviceObject);
    }

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        Object o = methodInvocation.proceed();
        invokeAdviceMethod();
        return o;
    }
}
