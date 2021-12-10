package com.my.factory.render;

import com.my.factory.model.Button;

public abstract class BaseButtonRender {

  public static BaseButtonRender init(String arg) {
    return switch (arg) {
      case "window" -> new WindowsButtonRender();
      case "html" -> new HtmlButtonRender();
      default -> throw new IllegalStateException(String.format("Unknown button render = {%s}", arg));
    };
  }

  public void renderWindow() {
    Button button = createButton();
    button.render();
  }

  protected abstract Button createButton();

}
