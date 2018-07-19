package com.yuanzhipeng.litespring.service.v4;

import com.yuanzhipeng.litespring.dao.v4.AccountDao;
import com.yuanzhipeng.litespring.dao.v4.ItemDao;

public class PetStroeService {
    private AccountDao accountDao;
    private ItemDao itemDao;
    private int version;
    
    public PetStroeService(AccountDao accountDao, ItemDao itemDao, int version) {
        super();
        this.accountDao = accountDao;
        this.itemDao = itemDao;
        this.version = version;
    }
    
    public PetStroeService(AccountDao accountDao, ItemDao itemDao) {
        super();
        this.accountDao = accountDao;
        this.itemDao = itemDao;
        this.version = -1;
    }
    
    public int getVersion() {
        return version;
    }
    public AccountDao getAccountDao() {
        return accountDao;
    }
    public ItemDao getItemDao() {
        return itemDao;
    }
}
