package com.my.factory.render;

import com.my.factory.model.Button;
import com.my.factory.model.HtmlButton;

public class HtmlButtonRender extends BaseButtonRender {

  @Override
  protected Button createButton() {
    return new HtmlButton();
  }
}
