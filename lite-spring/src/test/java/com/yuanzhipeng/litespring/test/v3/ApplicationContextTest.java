package com.yuanzhipeng.litespring.test.v3;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.yuanzhipeng.litespring.context.ApplicationContext;
import com.yuanzhipeng.litespring.context.support.ClassPathXmlApplicationContext;
import com.yuanzhipeng.litespring.service.v2.PetStroeService;

public class ApplicationContextTest {
    @Test
    public void testGetBean() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("petstore-v2.xml");
        PetStroeService petStrore = (PetStroeService)ctx.getBean("petStore");
        assertNotNull(petStrore);
        assertNotNull(petStrore.getAccountDao());
        assertNotNull(petStrore.getItemDao());
        assertTrue("bruce".equals(petStrore.getOwner()));
        assertTrue(4 == petStrore.getVersion());
    }
}
