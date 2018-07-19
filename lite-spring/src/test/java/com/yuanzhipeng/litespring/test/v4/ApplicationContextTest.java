package com.yuanzhipeng.litespring.test.v4;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.yuanzhipeng.litespring.context.ApplicationContext;
import com.yuanzhipeng.litespring.context.support.ClassPathXmlApplicationContext;
import com.yuanzhipeng.litespring.service.v4.PetStroeService;

public class ApplicationContextTest {
    @Test
    public void testGetBean() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("petstore-v4.xml");
        PetStroeService petStrore = (PetStroeService)ctx.getBean("petStore");
        assertNotNull(petStrore);
        assertNotNull(petStrore.getAccountDao());
        assertNotNull(petStrore.getItemDao());
        assertTrue(1 == petStrore.getVersion());
    }
}
