package com.yuanzhipeng.litespring.aop.aspectj;

import com.yuanzhipeng.litespring.aop.Advice;
import com.yuanzhipeng.litespring.aop.Pointcut;
import com.yuanzhipeng.litespring.beans.factory.config.AspectInstanceFactory;

import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;

public abstract class AbstractAspectJAdvice implements Advice {
    protected Method adviceMethod;
    protected Pointcut pc;
    protected AspectInstanceFactory adviceObjectFactory;

    public AbstractAspectJAdvice(Method adviceMethod, Pointcut pc, AspectInstanceFactory adviceObjectFactory) {
        this.adviceMethod = adviceMethod;
        this.pc = pc;
        this.adviceObjectFactory = adviceObjectFactory;
    }

    @Override
    public Pointcut getPointcut() {
        return this.pc;
    }

    public void invokeAdviceMethod() throws Throwable {
        adviceMethod.invoke(adviceObjectFactory.getAspectInstance());
    }
    
    public Method getAdviceMethod() {
        return adviceMethod;
    }
    public Object getAdviceInstance() throws Exception {
        return adviceObjectFactory.getAspectInstance();
    }
}
