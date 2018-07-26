package com.yuanzhipeng.litespring.core.type.classreading;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;

import com.yuanzhipeng.litespring.core.annotation.AnnotationAttributes;
import com.yuanzhipeng.litespring.core.type.AnnotationMetadata;

public class AnnotationMetadataReadingVisitor extends ClassMetadataReadingVisitor implements AnnotationMetadata{
    private final Set<String> annotationSet = new LinkedHashSet<>();
    private final Map<String, AnnotationAttributes> attributesMap = new LinkedHashMap<>();
    @Override
    public AnnotationVisitor visitAnnotation(String arg0, boolean arg1) {
        // TODO Auto-generated method stub
        String className = Type.getType(arg0).getClassName();
        annotationSet.add(className);
        return new AnnotationAttributeReadingVisitor(className, attributesMap);
    }

    @Override
    public void visitAttribute(Attribute arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void visitEnd() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public FieldVisitor visitField(int arg0, String arg1, String arg2, String arg3, Object arg4) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void visitInnerClass(String arg0, String arg1, String arg2, int arg3) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public MethodVisitor visitMethod(int arg0, String arg1, String arg2, String arg3, String[] arg4) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void visitOuterClass(String arg0, String arg1, String arg2) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void visitSource(String arg0, String arg1) {
        // TODO Auto-generated method stub
        
    }

    public Set<String> getAnnotationTypes() {
        return this.annotationSet;
    }

    public boolean hasAnnotation(String annotationType) {
        return this.annotationSet.contains(annotationType);
    }

    public AnnotationAttributes getAnnotationAttributes(String annotationType) {
        return this.attributesMap.get(annotationType);
    }
}
