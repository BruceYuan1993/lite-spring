package com.yuanzhipeng.litespring.test.v6;

import com.yuanzhipeng.litespring.aop.aspectj.AspectJAfterReturningAdvice;
import com.yuanzhipeng.litespring.aop.aspectj.AspectJAfterThrowingAdvice;
import com.yuanzhipeng.litespring.aop.aspectj.AspectJBeforeAdvice;
import com.yuanzhipeng.litespring.aop.aspectj.AspectJExpressionPointcut;
import com.yuanzhipeng.litespring.aop.framework.ReflectiveMethodInvocation;
import com.yuanzhipeng.litespring.beans.factory.BeanFactory;
import com.yuanzhipeng.litespring.beans.factory.config.AspectInstanceFactory;
import com.yuanzhipeng.litespring.service.v6.PetStroeService;
import com.yuanzhipeng.litespring.tx.TransactionManager;
import com.yuanzhipeng.litespring.util.MessageTracker;
import org.aopalliance.intercept.MethodInterceptor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ReflectiveMethodInvocationTest extends AbstractV6Test {
    
    private  AspectJBeforeAdvice beforeAdvice = null;
    private  AspectJAfterReturningAdvice afterAdvice = null;
    private  AspectJExpressionPointcut pc = null;
    private  BeanFactory beanFactory = null;
    private  AspectInstanceFactory aspectInstanceFactory = null;    
    
    private AspectJAfterThrowingAdvice  afterThrowingAdvice = null;
    private PetStroeService petStoreService = null;
    private TransactionManager tx;
    

    @Before
    public  void setUp() throws Exception{      
        petStoreService = new PetStroeService();
        tx = new TransactionManager();
        
        MessageTracker.clearMsgs();
        
        beanFactory = this.getBeanFactory("petstore-v6.xml");
        aspectInstanceFactory = this.getAspectInstanceFactory("tx");
        aspectInstanceFactory.setBeanFactory(beanFactory);
        
        beforeAdvice = new AspectJBeforeAdvice(
                this.getAdviceMethod("start"),
                null,
                aspectInstanceFactory);
        
        afterAdvice = new AspectJAfterReturningAdvice(
                this.getAdviceMethod("commit"),
                null,
                aspectInstanceFactory); 
        
        afterThrowingAdvice = new AspectJAfterThrowingAdvice(
                this.getAdviceMethod("rollback"),
                null,
                aspectInstanceFactory
                );
        
    }

    
    @Test
    public void testMethodInvocation() throws Throwable{
        
        
        Method targetMethod = PetStroeService.class.getMethod("placeOrder");
        
        List<MethodInterceptor> interceptors = new ArrayList<MethodInterceptor>();
        interceptors.add(beforeAdvice);
        interceptors.add(afterAdvice);  
        
        
        ReflectiveMethodInvocation mi = new ReflectiveMethodInvocation(petStoreService,targetMethod,new Object[0],interceptors);
        
        mi.proceed();
        
        
        List<String> msgs = MessageTracker.getMsgs();
        Assert.assertEquals(3, msgs.size());
        Assert.assertEquals("start tx", msgs.get(0));   
        Assert.assertEquals("place order", msgs.get(1));    
        Assert.assertEquals("commit tx", msgs.get(2));  
        
    }
    
    @Test
    public void testMethodInvocation2() throws Throwable{
        
        
        Method targetMethod = PetStroeService.class.getMethod("placeOrder");
        
        List<MethodInterceptor> interceptors = new ArrayList<MethodInterceptor>();
        interceptors.add(afterAdvice);  
        interceptors.add(beforeAdvice);
        
        
        
        ReflectiveMethodInvocation mi = new ReflectiveMethodInvocation(petStoreService,targetMethod,new Object[0],interceptors);
        
        mi.proceed();
        
        
        List<String> msgs = MessageTracker.getMsgs();
        Assert.assertEquals(3, msgs.size());
        Assert.assertEquals("start tx", msgs.get(0));   
        Assert.assertEquals("place order", msgs.get(1));    
        Assert.assertEquals("commit tx", msgs.get(2));  
        
    }
    @Test
    public void testAfterThrowing() throws Throwable{
        
        
        Method targetMethod = PetStroeService.class.getMethod("placeOrderWithException");
        
        List<MethodInterceptor> interceptors = new ArrayList<MethodInterceptor>();
        interceptors.add(afterThrowingAdvice);  
        interceptors.add(beforeAdvice);
        
        
        
        ReflectiveMethodInvocation mi = new ReflectiveMethodInvocation(petStoreService,targetMethod,new Object[0],interceptors);
        try{
            mi.proceed();   
            
        }catch(Throwable t){
            List<String> msgs = MessageTracker.getMsgs();
            Assert.assertEquals(2, msgs.size());
            Assert.assertEquals("start tx", msgs.get(0));           
            Assert.assertEquals("rollback tx", msgs.get(1));    
            return;
        }
            
        
        Assert.fail("No Exception thrown"); 
        
        
    }
    
}