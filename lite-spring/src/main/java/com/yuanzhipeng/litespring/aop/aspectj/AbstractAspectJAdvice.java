package com.yuanzhipeng.litespring.aop.aspectj;

import com.yuanzhipeng.litespring.aop.Advice;
import com.yuanzhipeng.litespring.aop.Pointcut;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;

public abstract class AbstractAspectJAdvice implements Advice {
    protected Method adviceMethod;
    protected Pointcut pc;
    protected Object adviceObject;

    public AbstractAspectJAdvice(Method adviceMethod, Pointcut pc, Object adviceObject) {
        this.adviceMethod = adviceMethod;
        this.pc = pc;
        this.adviceObject = adviceObject;
    }

    @Override
    public Pointcut getPointcut() {
        return this.pc;
    }

    public void invokeAdviceMethod() throws Throwable {
        adviceMethod.invoke(adviceObject);
    }
}
