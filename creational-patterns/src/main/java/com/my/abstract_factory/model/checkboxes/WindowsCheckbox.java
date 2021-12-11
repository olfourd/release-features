package com.my.abstract_factory.model.checkboxes;

public class WindowsCheckbox implements Checkbox {

  @Override
  public void paint() {
    System.out.println("You have created WindowsCheckbox.");
  }
}
