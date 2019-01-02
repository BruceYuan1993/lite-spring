package com.yuanzhipeng.litespring.aop;

import java.lang.reflect.Method;

public interface MethodMatcher {
    boolean matches(Method method);
}
