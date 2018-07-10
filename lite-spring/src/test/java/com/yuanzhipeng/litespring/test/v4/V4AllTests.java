package com.yuanzhipeng.litespring.test.v4;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ApplicationContextTest.class, BeanDefinitionTest.class,
    ConstructorResolverTest.class})
public class V4AllTests {

}
