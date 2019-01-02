package com.yuanzhipeng.litespring.aop;

public interface Pointcut {
    MethodMatcher getMethodMatcher();
    String getExpression();
}
