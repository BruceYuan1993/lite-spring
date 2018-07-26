package com.yuanzhipeng.litespring.test.v5;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.yuanzhipeng.litespring.core.annotation.AnnotationAttributes;
import com.yuanzhipeng.litespring.core.io.ClassPathResource;
import com.yuanzhipeng.litespring.core.type.AnnotationMetadata;
import com.yuanzhipeng.litespring.core.type.classreading.MetadataReader;
import com.yuanzhipeng.litespring.core.type.classreading.SimpleMetadataReader;
import com.yuanzhipeng.litespring.stereotype.Component;

public class MetadataReaderTest {
    @Test
    public void testGetMetadata() throws IOException {
        ClassPathResource resource = new ClassPathResource("com/yuanzhipeng/litespring/service/v5/PetStroeService.class");
        MetadataReader reader = new SimpleMetadataReader(resource);
        AnnotationMetadata amd = reader.getAnnotationMetadata();
        String annotation = Component.class.getName();
        
        Assert.assertTrue(amd.hasAnnotation(annotation));       
        AnnotationAttributes attributes = amd.getAnnotationAttributes(annotation);      
        Assert.assertEquals("petStore", attributes.get("value"));
        
        Assert.assertFalse(amd.isAbstract());       
        Assert.assertFalse(amd.isFinal());
        Assert.assertEquals("com.yuanzhipeng.litespring.service.v5.PetStroeService", amd.getClassName());
    }
}
