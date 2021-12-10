package com.my.factory;

import com.my.factory.render.BaseButtonRender;
import java.util.Arrays;
import lombok.AccessLevel;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class Client {

  private static BaseButtonRender buttonRender;

  public static void main(String[] args) {
    configure(args);
    runBusinessLogic();
  }

  private static void configure(String[] args) {
    String buttonArg = defineButtonArg(args);
    buttonRender = BaseButtonRender.init(buttonArg);
  }

  private static void runBusinessLogic() {
    buttonRender.renderWindow();
  }

  private static String defineButtonArg(String[] args) {
    return Arrays.stream(args)
        .filter(arg -> arg.contains("button"))
        .findFirst()
        .map(buttonArg -> StringUtils.substringAfter(buttonArg, "="))
        .orElseGet(() -> {
              String errMsg = "Error during defining buttonRender: arg 'button' not specified";
              log.error(errMsg);
              throw new IllegalArgumentException(errMsg);
            }
        );
  }
}
