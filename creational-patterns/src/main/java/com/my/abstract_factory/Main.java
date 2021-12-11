package com.my.abstract_factory;

import com.my.abstract_factory.factories.GUIFactory;
import com.my.utils.ArgsUtils;

public class Main {

  public static void main(String[] args) {
    Client application = configureApp(args);
    application.paint();
  }

  private static Client configureApp(String[] args) {
    String osArg = ArgsUtils.defineArg(args, "os_name");
    GUIFactory factory = GUIFactory.init(osArg);
    return new Client(factory);
  }
}
