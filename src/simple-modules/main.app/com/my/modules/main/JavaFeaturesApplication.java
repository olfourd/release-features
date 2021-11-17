package com.my.modules.main;

import com.my.modules.hello.HelloInterface;
import com.my.modules.hello.HelloModule;
import java.util.ServiceLoader;

public class JavaFeaturesApplication {

	public static void main(String[] args) {
		HelloModule.doSomething();

		Iterable<HelloInterface> services = ServiceLoader.load(HelloInterface.class);
		HelloInterface service = services.iterator().next();
		service.hello();
		service.smth();
	}

}
