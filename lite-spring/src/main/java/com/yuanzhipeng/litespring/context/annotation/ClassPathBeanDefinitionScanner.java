package com.yuanzhipeng.litespring.context.annotation;

import java.util.LinkedHashSet;
import java.util.Set;

import com.yuanzhipeng.litespring.beans.BeanDefinition;
import com.yuanzhipeng.litespring.beans.factory.BeanDefinitionStoreException;
import com.yuanzhipeng.litespring.beans.factory.support.BeanDefinitionRegistry;
import com.yuanzhipeng.litespring.beans.factory.support.BeanNameGenerator;
import com.yuanzhipeng.litespring.core.io.Resource;
import com.yuanzhipeng.litespring.core.io.support.PackageResourceLoader;
import com.yuanzhipeng.litespring.core.type.classreading.MetadataReader;
import com.yuanzhipeng.litespring.core.type.classreading.SimpleMetadataReader;
import com.yuanzhipeng.litespring.stereotype.Component;
import com.yuanzhipeng.litespring.util.StringUtils;

public class ClassPathBeanDefinitionScanner {
    private final BeanDefinitionRegistry registry;
    private final BeanNameGenerator beanNameGenerator = new AnnotationBeanNameGenerator();
    private PackageResourceLoader resourceLoader = new PackageResourceLoader();
    public ClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry) {
        // TODO Auto-generated constructor stub
        this.registry = registry;
    }

    public Set<BeanDefinition> doScan(String packagesToScan) {
        // TODO Auto-generated method stub
        String[] basePackages = StringUtils.tokenizeToStringArray(packagesToScan, ",");
        Set<BeanDefinition> beanDefinitions = new LinkedHashSet<>();
        
        for (String pkg : basePackages) {
            Set<BeanDefinition> candidates = findCandidatesComponents(pkg);
            for (BeanDefinition candidate : candidates) {
                beanDefinitions.add(candidate);
                registry.registryBeanDefinition(candidate.getId(), candidate);
            }
        }
        return beanDefinitions;
    }

    private Set<BeanDefinition> findCandidatesComponents(String pkg) {
        // TODO Auto-generated method stub
        Set<BeanDefinition> candidates = new LinkedHashSet<>();
        Resource[] resources = resourceLoader.getResources(pkg);
        for (Resource resource: resources) {
            try {
                MetadataReader reader = new SimpleMetadataReader(resource);
                if (reader.getAnnotationMetadata().hasAnnotation(Component.class.getName())) {
                    ScannedGenericBeanDefinition sbd= new ScannedGenericBeanDefinition(reader.getAnnotationMetadata());
                    String name = this.beanNameGenerator.generateBeanName(sbd, registry);
                    sbd.setId(name);
                    candidates.add(sbd);
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                throw new BeanDefinitionStoreException("Failed to read condidate compentent class.", e);
            }
        }
        return candidates;
    }

}
