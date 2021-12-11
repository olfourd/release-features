package com.my.abstract_factory.factories;

import com.my.abstract_factory.model.buttons.Button;
import com.my.abstract_factory.model.buttons.MacOsButton;
import com.my.abstract_factory.model.checkboxes.Checkbox;
import com.my.abstract_factory.model.checkboxes.MacOSCheckbox;

public class MacOSFactory implements GUIFactory {

  @Override
  public Button createButton() {
    return new MacOsButton();
  }

  @Override
  public Checkbox createCheckbox() {
    return new MacOSCheckbox();
  }
}
