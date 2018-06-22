package com.yuanzhipeng.litespring.core.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import com.yuanzhipeng.litespring.util.ClassUtils;

public class ClassPathResource implements Resource {
    private String fileName;
    private ClassLoader classLoader;
    
    public ClassPathResource(String fileName, ClassLoader classLoader) {
        this.fileName = fileName;
        this.classLoader = classLoader == null ? ClassUtils.getDefaultClassLoader() : classLoader;
    }

    public ClassPathResource(String fileName) {
        this(fileName, null);
    }

    @Override
    public InputStream getInputStream() throws IOException {
        // TODO Auto-generated method stub
        //ʹ��try-with-resource���ر�InputStream
        InputStream in = classLoader.getResourceAsStream(fileName);
        if (in == null) {
            throw new FileNotFoundException(fileName + " cannot be opened");
        }
        return in;
    }

    @Override
    public String getDescription() {
        // TODO Auto-generated method stub
        return this.fileName;
    }

}
