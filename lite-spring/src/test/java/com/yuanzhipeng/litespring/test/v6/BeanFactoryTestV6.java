package com.yuanzhipeng.litespring.test.v6;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.yuanzhipeng.litespring.aop.Advice;
import com.yuanzhipeng.litespring.aop.aspectj.AspectJAfterReturningAdvice;
import com.yuanzhipeng.litespring.aop.aspectj.AspectJAfterThrowingAdvice;
import com.yuanzhipeng.litespring.aop.aspectj.AspectJBeforeAdvice;
import com.yuanzhipeng.litespring.beans.factory.BeanFactory;
import com.yuanzhipeng.litespring.tx.TransactionManager;

public class BeanFactoryTestV6 extends AbstractV6Test {
    static String expectedExpression = "execution(* org.litespring.service.v6.*.placeOrder(..))";
    @Test
    public void testGetBeanByType() throws Exception{
        
        BeanFactory factory = super.getBeanFactory("petstore-v6.xml");
        
        List<Object> advices = factory.getBeansByType(Advice.class);
        
        Assert.assertEquals(3, advices.size());
        
        {
            AspectJBeforeAdvice advice = (AspectJBeforeAdvice)this.getAdvice(AspectJBeforeAdvice.class, advices);           
                    
            Assert.assertEquals(TransactionManager.class.getMethod("start"), advice.getAdviceMethod());
        
            Assert.assertEquals(expectedExpression,advice.getPointcut().getExpression());
            
            Assert.assertEquals(TransactionManager.class,advice.getAdviceInstance().getClass());
            
        }
        
        
        {
            AspectJAfterReturningAdvice advice = (AspectJAfterReturningAdvice)this.getAdvice(AspectJAfterReturningAdvice.class, advices);           
                    
            Assert.assertEquals(TransactionManager.class.getMethod("commit"), advice.getAdviceMethod());
        
            Assert.assertEquals(expectedExpression,advice.getPointcut().getExpression());
            
            Assert.assertEquals(TransactionManager.class,advice.getAdviceInstance().getClass());
            
        }
        
        {
            AspectJAfterThrowingAdvice advice = (AspectJAfterThrowingAdvice)this.getAdvice(AspectJAfterThrowingAdvice.class, advices);          
                    
            Assert.assertEquals(TransactionManager.class.getMethod("rollback"), advice.getAdviceMethod());
        
            Assert.assertEquals(expectedExpression,advice.getPointcut().getExpression());
            
            Assert.assertEquals(TransactionManager.class,advice.getAdviceInstance().getClass());
            
        }
        
        
    }
    
    public Object getAdvice(Class<?> type,List<Object> advices){
        for(Object o : advices){
            if(o.getClass().equals(type)){
                return o;
            }
        }
        return null;
    }
}
