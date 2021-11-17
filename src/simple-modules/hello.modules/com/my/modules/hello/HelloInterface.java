package com.my.modules.hello;

public interface HelloInterface {

    static void smth() {
        commonStaticFunc("smth!");
    }

    void hello();

    private static void commonStaticFunc(String args) {
        System.out.println(args);
    }
}
