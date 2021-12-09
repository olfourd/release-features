package com.my.javafeatures.java_14.sealed_class;

public abstract sealed class Shape permits Circle, Rectangle, Square {

    public String getClassName() {
        return this.getClass().getName();
    }
}
