package com.yuanzhipeng.litespring.core.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.yuanzhipeng.litespring.util.Assert;

public class FileSystemResource implements Resource {
    private final String path;
    private final File file;
    public FileSystemResource(String path) {
        Assert.notNull(path, "path cannot be null");
        this.path = path;
        this.file = new File(path);
    }
    @Override
    public InputStream getInputStream() throws IOException {
        // It will create new File instance everytime if use string constructor。
        return new FileInputStream(file);
    }

    @Override
    public String getDescription() {
        // TODO Auto-generated method stub
        return path;
    }

}
