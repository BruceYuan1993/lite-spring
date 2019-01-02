package com.yuanzhipeng.litespring.test.v6;

import com.yuanzhipeng.litespring.context.ApplicationContext;
import com.yuanzhipeng.litespring.context.support.ClassPathXmlApplicationContext;
import com.yuanzhipeng.litespring.service.v6.PetStroeService;
import com.yuanzhipeng.litespring.util.MessageTracker;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ApplicationContextTest {
    @Before
    public void setUp(){
        MessageTracker.clearMsgs();
    }

    @Test
    public void testPlaceOrder() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("petstore-v6.xml");
        PetStroeService petStrore = (PetStroeService)ctx.getBean("petStore");
        
        assertNotNull(petStrore);
        assertNotNull(petStrore.getAccountDao());
        assertNotNull(petStrore.getItemDao());

        petStrore.placeOrder();

        List<String> msgs = MessageTracker.getMsgs();

        assertEquals(3, msgs.size());
        assertEquals("start tx", msgs.get(0));
        assertEquals("place order", msgs.get(1));
        assertEquals("commit tx", msgs.get(2));
    }
}
