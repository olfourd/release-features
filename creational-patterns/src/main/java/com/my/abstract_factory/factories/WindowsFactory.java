package com.my.abstract_factory.factories;

import com.my.abstract_factory.model.buttons.Button;
import com.my.abstract_factory.model.buttons.WindowsButton;
import com.my.abstract_factory.model.checkboxes.Checkbox;
import com.my.abstract_factory.model.checkboxes.WindowsCheckbox;

public class WindowsFactory implements GUIFactory {

  @Override
  public Button createButton() {
    return new WindowsButton();
  }

  @Override
  public Checkbox createCheckbox() {
    return new WindowsCheckbox();
  }
}
