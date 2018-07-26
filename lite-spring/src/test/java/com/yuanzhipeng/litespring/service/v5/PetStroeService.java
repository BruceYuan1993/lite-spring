package com.yuanzhipeng.litespring.service.v5;

import com.yuanzhipeng.litespring.beans.factory.annotation.Autowired;
import com.yuanzhipeng.litespring.dao.v5.AccountDao;
import com.yuanzhipeng.litespring.dao.v5.ItemDao;
import com.yuanzhipeng.litespring.stereotype.Component;

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
}
