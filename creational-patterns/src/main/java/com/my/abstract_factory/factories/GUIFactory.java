package com.my.abstract_factory.factories;

import com.my.abstract_factory.model.buttons.Button;
import com.my.abstract_factory.model.checkboxes.Checkbox;

public interface GUIFactory {

  static GUIFactory init(String arg) {
    return switch (arg) {
      case "mac" -> new MacOSFactory();
      case "windows" -> new WindowsFactory();
      default -> throw new IllegalStateException(String.format("Unknown factory with arg = {%s}", arg));
    };
  }

  Button createButton();

  Checkbox createCheckbox();

}
