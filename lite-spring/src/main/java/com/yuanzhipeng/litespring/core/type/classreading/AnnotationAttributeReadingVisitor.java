package com.yuanzhipeng.litespring.core.type.classreading;

import java.util.Map;

import org.objectweb.asm.AnnotationVisitor;

import com.yuanzhipeng.litespring.core.annotation.AnnotationAttributes;

public class AnnotationAttributeReadingVisitor implements AnnotationVisitor {
    private final String annotationType;
    private final Map<String, AnnotationAttributes> attributesMap;
    AnnotationAttributes attributes = new AnnotationAttributes();
    public AnnotationAttributeReadingVisitor(String annotationType, Map<String, AnnotationAttributes> attributesMap) {
        super();
        this.annotationType = annotationType;
        this.attributesMap = attributesMap;
    }

    @Override
    public void visit(String arg0, Object arg1) {
        // TODO Auto-generated method stub
        this.attributes.put(arg0, arg1);
    }

    @Override
    public AnnotationVisitor visitAnnotation(String arg0, String arg1) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public AnnotationVisitor visitArray(String arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void visitEnd() {
        // TODO Auto-generated method stub
        this.attributesMap.put(annotationType, attributes);
    }

    @Override
    public void visitEnum(String arg0, String arg1, String arg2) {
        // TODO Auto-generated method stub

    }

}
