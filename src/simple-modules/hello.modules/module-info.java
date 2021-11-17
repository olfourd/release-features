module hello.modules {
    exports com.my.modules.hello;
    provides com.my.modules.hello.TestInterface with com.my.modules.hello.HelloModule;
}