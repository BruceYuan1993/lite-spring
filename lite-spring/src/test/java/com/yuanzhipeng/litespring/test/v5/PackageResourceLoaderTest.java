package com.yuanzhipeng.litespring.test.v5;

import org.junit.Assert;
import org.junit.Test;

import com.yuanzhipeng.litespring.core.io.Resource;
import com.yuanzhipeng.litespring.core.io.support.PackageResourceLoader;

public class PackageResourceLoaderTest {
    @Test
    public void testGetResources() {
        PackageResourceLoader loader = new PackageResourceLoader();
        Resource[] resources = loader.getResources("com.yuanzhipeng.litespring.dao.v5");
        Assert.assertTrue(resources.length == 2);
    }
}
