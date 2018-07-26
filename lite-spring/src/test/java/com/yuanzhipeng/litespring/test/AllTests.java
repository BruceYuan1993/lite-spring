package com.yuanzhipeng.litespring.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.yuanzhipeng.litespring.test.v1.V1AllTests;
import com.yuanzhipeng.litespring.test.v3.V3AllTests;
import com.yuanzhipeng.litespring.test.v4.V4AllTests;
import com.yuanzhipeng.litespring.test.v5.V5AllTests;

@RunWith(Suite.class)
@SuiteClasses({ V3AllTests.class, V1AllTests.class, V4AllTests.class,
    V5AllTests.class})
public class AllTests {

}

