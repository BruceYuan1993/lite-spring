package com.yuanzhipeng.litespring.test.v3;

import org.junit.Assert;
import org.junit.Test;

import com.yuanzhipeng.litespring.beans.propertyeditors.CustomNumberEditor;


public class CustomNumberEditorTest {
    @Test
    public void testConvertString() {
        CustomNumberEditor editor = new CustomNumberEditor(Integer.class, true);
        editor.setAsText("3");
        Object value = editor.getValue();
        Assert.assertTrue(value instanceof Integer);
        Assert.assertTrue(3 == ((Integer)value).intValue());
        
        editor.setAsText("");
        Assert.assertTrue(editor.getValue() == null);
        
        try {
            editor.setAsText("3.1");
        } catch (IllegalArgumentException e) {
            return;
        }
        Assert.fail();
    }
}
