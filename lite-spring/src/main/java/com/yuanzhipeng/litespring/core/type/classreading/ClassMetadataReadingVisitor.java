package com.yuanzhipeng.litespring.core.type.classreading;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import com.yuanzhipeng.litespring.core.type.ClassMetadata;
import com.yuanzhipeng.litespring.util.ClassUtils;

public class ClassMetadataReadingVisitor implements ClassVisitor, ClassMetadata{
    private String className;
    private boolean isInterface;
    private boolean isAbstract;
    private boolean isFinal;
    private String superClassName;
    private String[] interfaces;
    public void visit(int arg0, int arg1, String arg2, String arg3, String arg4, String[] arg5) {
        // TODO Auto-generated method stub
        this.className = ClassUtils.convertResourcePathToClassName(arg2);
        if (arg4 != null) {
            this.superClassName = ClassUtils.convertResourcePathToClassName(arg4);
        }
        this.isInterface = ((arg1 & Opcodes.ACC_INTERFACE) != 0);
        this.isAbstract = ((arg1 & Opcodes.ACC_ABSTRACT) != 0);
        this.isFinal = ((arg1 & Opcodes.ACC_FINAL) != 0);
        this.interfaces = new String[arg5.length];
        int i = 0;
        for (String s : arg5) {
            
            this.interfaces[i] = ClassUtils.convertResourcePathToClassName(s);
            i++;
        }
    }

    public String getClassName() {
        return className;
    }

    public boolean isInterface() {
        return isInterface;
    }

    public boolean isAbstract() {
        return isAbstract;
    }

    public boolean isFinal() {
        return isFinal;
    }

    public String getSuperClassName() {
        return superClassName;
    }

    public String[] getInterfaces() {
        return interfaces;
    }

    public AnnotationVisitor visitAnnotation(String arg0, boolean arg1) {
        // TODO Auto-generated method stub
        return null;
    }

    public void visitAttribute(Attribute arg0) {
        // TODO Auto-generated method stub
        
    }

    public void visitEnd() {
        // TODO Auto-generated method stub
        
    }

    public FieldVisitor visitField(int arg0, String arg1, String arg2, String arg3, Object arg4) {
        // TODO Auto-generated method stub
        return null;
    }

    public void visitInnerClass(String arg0, String arg1, String arg2, int arg3) {
        // TODO Auto-generated method stub
        
    }

    public MethodVisitor visitMethod(int arg0, String arg1, String arg2, String arg3, String[] arg4) {
        // TODO Auto-generated method stub
        return null;
    }

    public void visitOuterClass(String arg0, String arg1, String arg2) {
        // TODO Auto-generated method stub
        
    }

    public void visitSource(String arg0, String arg1) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean hasSuperClass() {
        // TODO Auto-generated method stub
        return superClassName != null;
    }

    @Override
    public String[] getInterfaceNames() {
        // TODO Auto-generated method stub
        return interfaces;
    }
    
}
