package com.yuanzhipeng.litespring.beans;

public class PropertyValue {
    private final String name;
    private final Object value;
    private Object convertedValue;
    private boolean isConverted = false;
    public PropertyValue(String name, Object value) {
        super();
        this.name = name;
        this.value = value;
    }
    public String getName() {
        return name;
    }
    public Object getValue() {
        return value;
    }
    public boolean isConverted() {
        return isConverted;
    }
    public Object getConvertedValue() {
        return convertedValue;
    }
}
