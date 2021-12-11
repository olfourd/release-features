package com.my.abstract_factory;

import com.my.abstract_factory.factories.GUIFactory;
import com.my.abstract_factory.model.buttons.Button;
import com.my.abstract_factory.model.checkboxes.Checkbox;

public class Client {

  private final Button button;
  private final Checkbox checkbox;

  public Client(GUIFactory factory) {
    this.button = factory.createButton();
    this.checkbox = factory.createCheckbox();
  }

  public void paint() {
    button.paint();
    checkbox.paint();
  }
}
