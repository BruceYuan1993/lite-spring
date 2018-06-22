package com.yuanzhipeng.litespring.test.v1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.InputStream;

import org.junit.Test;

import com.yuanzhipeng.litespring.core.io.ClassPathResource;
import com.yuanzhipeng.litespring.core.io.FileSystemResource;
import com.yuanzhipeng.litespring.core.io.Resource;



public class ResourceTest {
    @Test
    public void testClassPathResource() {
        Resource r = new ClassPathResource("petstore-v1.xml");
        try (InputStream in = r.getInputStream()){
            assertNotNull(in);
        } catch(Exception e) {
            fail(e.getMessage());
        }
    }
    
    @Test
    public void testFileSystemResource() {
        Resource r = new FileSystemResource("D:\\spring\\lite-spring\\lite-spring\\src\\test\\resources\\petstore-v1.xml");
        try (InputStream in = r.getInputStream()){
            assertNotNull(in);
        } catch(Exception e) {
            fail(e.getMessage());
        }
    }
}
