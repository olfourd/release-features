package com.my.modules.hello;

public class HelloModule implements HelloInterface {

    @Override
    public void hello() {
        System.out.println("Hello module");
    }
}
