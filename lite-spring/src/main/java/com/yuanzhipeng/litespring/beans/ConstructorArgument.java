package com.yuanzhipeng.litespring.beans;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



public class ConstructorArgument {
    private List<ValueHolder> argumentValues = new ArrayList<ValueHolder>();
    public List<ValueHolder> getArgumentValues() {
        // TODO Auto-generated method stub
        return Collections.unmodifiableList(argumentValues);
    }
    
    
    public void addArgumentValue(ValueHolder holder) {
        this.argumentValues.add(holder);
    }
    
    
    public void addArgumentValue(Object value, String type) {
        this.argumentValues.add(new ValueHolder(value, type));
    }
    
    
    public int getArgumentCount() {
        // TODO Auto-generated method stub
        return argumentValues.size() ;
    }
    
    
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return argumentValues.isEmpty();
    }
    
    
    public void clear() {
        // TODO Auto-generated method stub
        argumentValues.clear();
    }

    public static class ValueHolder {
        private Object value;
        private String type;
        private String name;
        public ValueHolder(Object value, String type) {
            // TODO Auto-generated constructor stub
            this.value = value;
            this.type = type;
        }

        public ValueHolder(Object value) {
            super();
            this.value = value;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        public Object getValue() {
            // TODO Auto-generated method stub
            return this.value;
        }

    }
}
