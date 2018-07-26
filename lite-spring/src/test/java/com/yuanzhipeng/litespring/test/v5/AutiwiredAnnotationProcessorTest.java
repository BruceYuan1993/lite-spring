package com.yuanzhipeng.litespring.test.v5;

import java.lang.reflect.Field;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.yuanzhipeng.litespring.beans.factory.annotation.AutowiredAnnotationProcessor;
import com.yuanzhipeng.litespring.beans.factory.annotation.AutowiredFieldElement;
import com.yuanzhipeng.litespring.beans.factory.annotation.InjectionElement;
import com.yuanzhipeng.litespring.beans.factory.annotation.InjectionMetadata;
import com.yuanzhipeng.litespring.beans.factory.config.DependencyDescriptor;
import com.yuanzhipeng.litespring.beans.factory.support.DefaultBeanFactory;
import com.yuanzhipeng.litespring.dao.v5.AccountDao;
import com.yuanzhipeng.litespring.dao.v5.ItemDao;
import com.yuanzhipeng.litespring.service.v5.PetStroeService;

public class AutiwiredAnnotationProcessorTest {
    AccountDao accountDao = new AccountDao();
    ItemDao itemDao = new ItemDao();
    
    DefaultBeanFactory beanFactory = new DefaultBeanFactory() {
        public Object resolveDependency(DependencyDescriptor descriptor) {
            // TODO Auto-generated method stub
            if (descriptor.getDependencyType().equals(AccountDao.class)) {
                return accountDao;
            }
            if (descriptor.getDependencyType().equals(ItemDao.class)) {
                return itemDao;
            }
            return null;
        }
    };
    
    @Test
    public void testGetInjectionMetadata() {
        AutowiredAnnotationProcessor processor = new AutowiredAnnotationProcessor();
        processor.setBeanFactory(beanFactory);
        
        InjectionMetadata injectionMetadata = processor.buildAutowiringMetadata(PetStroeService.class);
        List<InjectionElement> elements = injectionMetadata.getInjectionElements();
        Assert.assertEquals(2, elements.size());
        
        assertFieldExists(elements,"accountDao");
        assertFieldExists(elements,"itemDao");
        
        PetStroeService petStore = new PetStroeService();
        injectionMetadata.inject(petStore);
        Assert.assertTrue(petStore.getAccountDao() instanceof AccountDao);
        Assert.assertTrue(petStore.getItemDao() instanceof ItemDao);
    }
    
    private void assertFieldExists(List<InjectionElement> elements ,String fieldName){
        for(InjectionElement ele : elements){
            AutowiredFieldElement fieldEle = (AutowiredFieldElement)ele;
            Field f = fieldEle.getField();
            if(f.getName().equals(fieldName)){
                return;
            }
        }
        Assert.fail(fieldName + "does not exist!");
    }
}
