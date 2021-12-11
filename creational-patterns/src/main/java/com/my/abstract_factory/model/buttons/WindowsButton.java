package com.my.abstract_factory.model.buttons;

public class WindowsButton implements Button {

  @Override
  public void paint() {
    System.out.println("You have created WindowsButton.");
  }
}
