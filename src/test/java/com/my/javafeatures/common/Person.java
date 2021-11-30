package com.my.javafeatures.common;

import lombok.ToString;

@ToString
public class Person {

    private static Long DEFAULT_USER_GROUP;

    private final Long id;
    private final String name;
    private final String description;

    static {
        DEFAULT_USER_GROUP = 200L;
        System.out.println("Person.static block");
    }

    {
        this.description = "default person description";
        System.out.println("Person. nonstatic block");
    }

    public Person(Long id, String name) {
        this.id = id;
        this.name = name;
        System.out.println("Person. Constructor");
    }

}
