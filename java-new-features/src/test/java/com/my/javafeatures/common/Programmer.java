package com.my.javafeatures.common;

public class Programmer extends Person {

    static {
        System.out.println("Programmer. static block");
    }

    {
        System.out.println("Programmer. nonstatic block");
    }

    public Programmer(Long id, String name) {
        super(id, name);
        System.out.println("Programmer. Constructor");
    }
}
