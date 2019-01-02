package com.yuanzhipeng.litespring.aop.aspectj;

import com.yuanzhipeng.litespring.aop.Advice;
import com.yuanzhipeng.litespring.aop.Pointcut;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;

public class AspectJAfterThrowingAdvice extends AbstractAspectJAdvice {

    public AspectJAfterThrowingAdvice(Method adviceMethod, Pointcut pc, Object adviceObject) {
        super(adviceMethod, pc, adviceObject);
    }

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        try {
            return methodInvocation.proceed();
        } catch (Throwable t) {
            invokeAdviceMethod();
            throw t;
        }
    }
}
