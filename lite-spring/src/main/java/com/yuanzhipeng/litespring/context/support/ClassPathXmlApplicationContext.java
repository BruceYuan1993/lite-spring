package com.yuanzhipeng.litespring.context.support;

import com.yuanzhipeng.litespring.core.io.ClassPathResource;
import com.yuanzhipeng.litespring.core.io.Resource;

public class ClassPathXmlApplicationContext extends AbstractApplicationContext{

    public ClassPathXmlApplicationContext(String configFile) {
        super(configFile);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected Resource getResourceByPath(String path) {
        // TODO Auto-generated method stub
        return new ClassPathResource(path, this.getBeanClassLoader());
    }

    
}
