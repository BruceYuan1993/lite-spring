package com.yuanzhipeng.litespring.test.v5;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.objectweb.asm.ClassReader;

import com.yuanzhipeng.litespring.core.annotation.AnnotationAttributes;
import com.yuanzhipeng.litespring.core.io.ClassPathResource;
import com.yuanzhipeng.litespring.core.type.classreading.AnnotationMetadataReadingVisitor;
import com.yuanzhipeng.litespring.core.type.classreading.ClassMetadataReadingVisitor;

public class ClassReaderTest {
    @Test
    public void testGetClassMetaData() throws IOException{
        ClassPathResource resource = new ClassPathResource("com/yuanzhipeng/litespring/service/v5/PetStroeService.class");
        ClassReader reader = new ClassReader(resource.getInputStream());
        
        ClassMetadataReadingVisitor visitor = new ClassMetadataReadingVisitor();
        reader.accept(visitor, ClassReader.SKIP_DEBUG);
        
        Assert.assertFalse(visitor.isAbstract());
        Assert.assertFalse(visitor.isFinal());
        Assert.assertFalse(visitor.isInterface());
        Assert.assertTrue("com.yuanzhipeng.litespring.service.v5.PetStroeService".equals(visitor.getClassName()));
        Assert.assertTrue("java.lang.Object".equals(visitor.getSuperClassName()));
        Assert.assertTrue(0 == visitor.getInterfaces().length);
    }
    
    @Test
    public void testGetAnnotation() throws IOException{
        ClassPathResource resource = new ClassPathResource("com/yuanzhipeng/litespring/service/v5/PetStroeService.class");
        ClassReader reader = new ClassReader(resource.getInputStream());
        
        AnnotationMetadataReadingVisitor visitor = new AnnotationMetadataReadingVisitor();
        reader.accept(visitor, ClassReader.SKIP_DEBUG);
        
        
        String annotation = "com.yuanzhipeng.litespring.stereotype.Component";
        Assert.assertTrue(visitor.hasAnnotation(annotation));
        
        AnnotationAttributes attributes = visitor.getAnnotationAttributes(annotation);
        Assert.assertTrue("petStore".equals(attributes.get("value")));
//        Assert.assertFalse(visitor.isAbstract());
//        Assert.assertFalse(visitor.isFinal());
//        Assert.assertFalse(visitor.isInterface());
//        Assert.assertTrue("com.yuanzhipeng.litespring.service.v5.PetStroeService".equals(visitor.getClassName()));
//        Assert.assertTrue("java.lang.Object".equals(visitor.getSuperClassName()));
//        Assert.assertTrue(0 == visitor.getInterfaces().length);
    }
}
