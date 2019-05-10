package com.yuanzhipeng.litespring.beans.factory;

import com.yuanzhipeng.litespring.beans.BeansException;

public class BeanDefinitionStoreException extends BeansException{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public BeanDefinitionStoreException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public BeanDefinitionStoreException(String msg) {
        super(msg);
    }

}
