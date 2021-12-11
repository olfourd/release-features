package com.my.factory;

import com.my.factory.render.BaseButtonRender;
import com.my.utils.ArgsUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Client {

  private static BaseButtonRender buttonRender;

  public static void main(String[] args) {
    configure(args);
    runBusinessLogic();
  }

  private static void configure(String[] args) {
    String buttonArg = ArgsUtils.defineArg(args, "button");
    buttonRender = BaseButtonRender.init(buttonArg);
  }

  private static void runBusinessLogic() {
    buttonRender.renderWindow();
  }
}
