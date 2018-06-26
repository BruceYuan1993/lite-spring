package com.yuanzhipeng.litespring.beans.propertyeditors;


import java.beans.PropertyEditorSupport;
import java.text.NumberFormat;

import com.yuanzhipeng.litespring.util.NumberUtils;
import com.yuanzhipeng.litespring.util.StringUtils;

public class CustomNumberEditor extends PropertyEditorSupport{
    private final Class<? extends Number> numberClass;
    private final NumberFormat numberFormat;
    private final boolean allowEmpty;
    public CustomNumberEditor(Class<Integer> numberClass, NumberFormat numberFormat, boolean allowEmpty) throws IllegalArgumentException{
        // TODO Auto-generated constructor stub
        if (numberClass == null || !Number.class.isAssignableFrom(numberClass)) {
            throw new IllegalArgumentException("Property class must be a subclass of Number");
        }
        this.numberClass = numberClass;
        this.numberFormat = numberFormat;
        this.allowEmpty = allowEmpty;
    }

    public CustomNumberEditor(Class<Integer> numberClass,  boolean allowEmpty) throws IllegalArgumentException{
        // TODO Auto-generated constructor stub
        this(numberClass, null, allowEmpty);
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        // TODO Auto-generated method stub
        if (this.allowEmpty && !StringUtils.hasText(text)) {
            setValue(null);
        } else if (this.numberFormat != null) {
            setValue(NumberUtils.parseNumber(text, this.numberClass, numberFormat));
        } else {
            setValue(NumberUtils.parseNumber(text, this.numberClass));
        }
    }

    @Override
    public void setValue(Object value) {
        // TODO Auto-generated method stub
        if (value instanceof Number) {
            super.setValue(NumberUtils.convertNumberToTargetClass((Number)value, this.numberClass));
        } else {
            super.setValue(value);
        }
        
    }

    @Override
    public String getAsText() {
        // TODO Auto-generated method stub
        Object value = getValue();
        if (value == null) {
            return "";
        }
        if (this.numberFormat != null) {
            // Use NumberFormat for rendering value.
            return this.numberFormat.format(value);
        }
        else {
            // Use toString method for rendering value.
            return value.toString();
        }
    }
    
    
    
    
}
