package com.yuanzhipeng.litespring.context.support;

import com.yuanzhipeng.litespring.core.io.FileSystemResource;
import com.yuanzhipeng.litespring.core.io.Resource;

public class FileSystemXmlApplicationContext extends AbstractApplicationContext{

    public FileSystemXmlApplicationContext(String configFile) {
        super(configFile);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected Resource getResourceByPath(String path) {
        // TODO Auto-generated method stub
        return new FileSystemResource(path);
    }
    
}
