package com.yuanzhipeng.litespring.test.v3;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ApplicationContextTest.class, BeanDefinitionTest.class,
    BeanDefinitionValueResolverTest.class, CustomNumberEditorTest.class,
    CustomBooleanEditorTest.class, TypeConverterTest.class})
public class V3AllTests {

}
