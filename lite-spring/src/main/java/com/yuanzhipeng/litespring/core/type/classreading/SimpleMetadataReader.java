package com.yuanzhipeng.litespring.core.type.classreading;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.objectweb.asm.ClassReader;

import com.yuanzhipeng.litespring.core.io.Resource;
import com.yuanzhipeng.litespring.core.type.AnnotationMetadata;
import com.yuanzhipeng.litespring.core.type.ClassMetadata;

public class SimpleMetadataReader implements MetadataReader {
    private final Resource resource;
    private final ClassMetadata classMetadata;
    private final AnnotationMetadata annotationMetadata;
    public SimpleMetadataReader(Resource resource) throws IOException {
        super();
        
        InputStream in = new BufferedInputStream(resource.getInputStream());
        ClassReader classReader;
        try {
            classReader = new ClassReader(in);
        } finally {
            in.close();
        }
        
        AnnotationMetadataReadingVisitor visitor = new AnnotationMetadataReadingVisitor();
        classReader.accept(visitor, ClassReader.SKIP_DEBUG);
        
        this.resource = resource;
        this.classMetadata = visitor;
        this.annotationMetadata = visitor;
    }

    @Override
    public Resource getResource() {
        // TODO Auto-generated method stub
        return this.resource;
    }

    @Override
    public ClassMetadata getClassMetadata() {
        // TODO Auto-generated method stub
        return this.classMetadata;
    }

    @Override
    public AnnotationMetadata getAnnotationMetadata() {
        // TODO Auto-generated method stub
        return this.annotationMetadata;
    }

}
