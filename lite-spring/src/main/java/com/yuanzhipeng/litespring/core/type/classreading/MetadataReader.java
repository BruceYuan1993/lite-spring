package com.yuanzhipeng.litespring.core.type.classreading;

import com.yuanzhipeng.litespring.core.io.Resource;
import com.yuanzhipeng.litespring.core.type.AnnotationMetadata;
import com.yuanzhipeng.litespring.core.type.ClassMetadata;

public interface MetadataReader {
    Resource getResource();
    ClassMetadata getClassMetadata();
    AnnotationMetadata getAnnotationMetadata();

}
