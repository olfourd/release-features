package com.my.factory.render;

import com.my.factory.model.Button;
import com.my.factory.model.WindowsButton;

public class WindowsButtonRender extends BaseButtonRender {

  @Override
  protected Button createButton() {
    return new WindowsButton();
  }
}
