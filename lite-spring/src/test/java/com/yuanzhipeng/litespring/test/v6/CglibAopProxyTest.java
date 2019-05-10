package com.yuanzhipeng.litespring.test.v6;

import com.yuanzhipeng.litespring.aop.aspectj.AspectJAfterReturningAdvice;
import com.yuanzhipeng.litespring.aop.aspectj.AspectJBeforeAdvice;
import com.yuanzhipeng.litespring.aop.aspectj.AspectJExpressionPointcut;
import com.yuanzhipeng.litespring.aop.framework.AopConfig;
import com.yuanzhipeng.litespring.aop.framework.AopConfigSupport;
import com.yuanzhipeng.litespring.aop.framework.CglibProxyFactory;
import com.yuanzhipeng.litespring.beans.factory.BeanFactory;
import com.yuanzhipeng.litespring.beans.factory.config.AspectInstanceFactory;
import com.yuanzhipeng.litespring.service.v6.PetStroeService;
import com.yuanzhipeng.litespring.tx.TransactionManager;
import com.yuanzhipeng.litespring.util.MessageTracker;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class CglibAopProxyTest extends AbstractV6Test{
    
    private  AspectJBeforeAdvice beforeAdvice = null;
    private  AspectJAfterReturningAdvice afterAdvice = null;
    private  AspectJExpressionPointcut pc = null;
    private  BeanFactory beanFactory = null;
    private  AspectInstanceFactory aspectInstanceFactory = null;
    
    @Before
    public  void setUp() throws Exception{      
        
        MessageTracker.clearMsgs();
        
        String expression = "execution(* com.yuanzhipeng.litespring.service.v6.*.placeOrder(..))";
        pc = new AspectJExpressionPointcut();
        pc.setExpression(expression);
        
        beanFactory = this.getBeanFactory("petstore-v6.xml");
        aspectInstanceFactory = this.getAspectInstanceFactory("tx");
        aspectInstanceFactory.setBeanFactory(beanFactory);
        
        beforeAdvice = new AspectJBeforeAdvice(
                getAdviceMethod("start"),
                pc,
                aspectInstanceFactory);
        
        afterAdvice = new AspectJAfterReturningAdvice(
                getAdviceMethod("commit"),
                pc,
                aspectInstanceFactory);     
        
    }
    
    @Test
    public void testGetProxy(){
        
        AopConfig config = new AopConfigSupport();
        
        config.addAdvice(beforeAdvice);
        config.addAdvice(afterAdvice);
        config.setTargetObject(new PetStroeService());
        
        
        CglibProxyFactory proxyFactory = new CglibProxyFactory(config);
        
        PetStroeService proxy = (PetStroeService)proxyFactory.getProxy();
        
        proxy.placeOrder();             
        
        
        List<String> msgs = MessageTracker.getMsgs();
        Assert.assertEquals(3, msgs.size());
        Assert.assertEquals("start tx", msgs.get(0));   
        Assert.assertEquals("place order", msgs.get(1));    
        Assert.assertEquals("commit tx", msgs.get(2));  
        
        proxy.toString();
    }
    
    
}