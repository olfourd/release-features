package com.my.abstract_factory.model.buttons;

public class MacOsButton implements Button {

  @Override
  public void paint() {
    System.out.println("You have created MacOSButton.");
  }
}
