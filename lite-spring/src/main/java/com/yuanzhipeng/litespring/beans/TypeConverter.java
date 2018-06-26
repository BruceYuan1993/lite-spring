package com.yuanzhipeng.litespring.beans;

public interface TypeConverter {

    < T> T convertIfNecessary(Object value, Class<T> target);

}
