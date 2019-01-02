package com.yuanzhipeng.litespring.service.v6;

import com.yuanzhipeng.litespring.beans.factory.annotation.Autowired;
import com.yuanzhipeng.litespring.dao.v5.AccountDao;
import com.yuanzhipeng.litespring.dao.v5.ItemDao;
import com.yuanzhipeng.litespring.stereotype.Component;
import com.yuanzhipeng.litespring.util.MessageTracker;

@Component("petStore")
public class PetStroeService {
    @Autowired
    private AccountDao accountDao;
    @Autowired
    private ItemDao itemDao;
    
    public AccountDao getAccountDao() {
        return accountDao;
    }
    public ItemDao getItemDao() {
        return itemDao;
    }


    public void placeOrder() {
        System.out.println("place order");
        MessageTracker.addMsg("place order");
    }

    public void placeOrderWithException() {
        throw new RuntimeException();
    }
}
