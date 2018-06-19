package com.yuanzhipeng.litespring.beans.factory;

import com.yuanzhipeng.litespring.beans.BeansException;

public class BeanCreationException extends BeansException{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String beanName;
    public BeanCreationException(String msg, Throwable cause) {
        super(msg, cause);
        // TODO Auto-generated constructor stub
    }
    public BeanCreationException(String msg) {
        super(msg);
        // TODO Auto-generated constructor stub
    }
    
    public BeanCreationException(String beanName, String msg) {
        super("Error creating bean with name '" + beanName +"': " +msg);
        this.beanName = beanName;
        // TODO Auto-generated constructor stub
    }
    
    public BeanCreationException(String beanName, String msg, Throwable cause) {
        this(beanName, msg);
        initCause(cause);
        // TODO Auto-generated constructor stub
    }
    public String getBeanName() {
        return beanName;
    }
    
    

}
