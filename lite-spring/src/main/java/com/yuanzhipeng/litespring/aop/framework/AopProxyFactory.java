package com.yuanzhipeng.litespring.aop.framework;

public interface AopProxyFactory {
    Object getProxy();
    Object getProxy(ClassLoader classLoader);
}
