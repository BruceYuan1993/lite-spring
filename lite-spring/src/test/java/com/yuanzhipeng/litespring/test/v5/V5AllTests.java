package com.yuanzhipeng.litespring.test.v5;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ApplicationContextTest.class, AutiwiredAnnotationProcessorTest.class,
        ClassPathBeanDefinitionScannerTest.class, ClassReaderTest.class, DependencyDescriptorTest.class,
        InjectionMetadataTest.class, MetadataReaderTest.class, PackageResourceLoaderTest.class,
        XmlBeanDefinitionReaderTest.class })
public class V5AllTests {

}
