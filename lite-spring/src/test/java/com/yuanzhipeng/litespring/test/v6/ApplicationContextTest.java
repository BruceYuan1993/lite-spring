package com.yuanzhipeng.litespring.test.v5;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.yuanzhipeng.litespring.context.ApplicationContext;
import com.yuanzhipeng.litespring.context.support.ClassPathXmlApplicationContext;
import com.yuanzhipeng.litespring.service.v5.PetStroeService;

public class ApplicationContextTest {
    @Test
    public void testGetBean() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("petstore-v5.xml");
        PetStroeService petStrore = (PetStroeService)ctx.getBean("petStore");
        
        assertNotNull(petStrore);
        assertNotNull(petStrore.getAccountDao());
        assertNotNull(petStrore.getItemDao());
    }
}
