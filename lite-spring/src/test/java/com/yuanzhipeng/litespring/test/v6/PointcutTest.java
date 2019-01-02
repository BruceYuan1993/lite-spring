package com.yuanzhipeng.litespring.test.v6;

import com.yuanzhipeng.litespring.aop.MethodMatcher;
import com.yuanzhipeng.litespring.aop.aspectj.AspectJExpressionPointcut;
import com.yuanzhipeng.litespring.service.v6.PetStroeService;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Method;

public class PointcutTest {
    @Test
    public void testPointcut() throws Exception {
    String expression = "execution(* com.yuanzhipeng.litespring.service.v6.*.placeOrder(..))";

    AspectJExpressionPointcut pc = new AspectJExpressionPointcut();
    pc.setExpression(expression);

    MethodMatcher mm = pc.getMethodMatcher();
        {
            Class<?> targetClass = PetStroeService.class;
            Method method1 = targetClass.getMethod("placeOrder");
            Assert.assertTrue(mm.matches(method1));

            Method method2 = targetClass.getMethod("getAccountDao");
            Assert.assertFalse(mm.matches(method2));
        }
    }
}
