package com.yuanzhipeng.litespring.service.v2;

import com.yuanzhipeng.litespring.dao.v2.AccountDao;
import com.yuanzhipeng.litespring.dao.v2.ItemDao;

public class PetStroeService {
    private AccountDao accountDao;
    private ItemDao itemDao;
    private String owner;
    private int version;
    public int getVersion() {
        return version;
    }
    public void setVersion(int version) {
        this.version = version;
    }
    public String getOwner() {
        return owner;
    }
    public void setOwner(String owner) {
        this.owner = owner;
    }
    public AccountDao getAccountDao() {
        return accountDao;
    }
    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }
    public ItemDao getItemDao() {
        return itemDao;
    }
    public void setItemDao(ItemDao itemDao) {
        this.itemDao = itemDao;
    }
}
