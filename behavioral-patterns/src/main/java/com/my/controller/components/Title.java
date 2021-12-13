package com.my.controller.components;

import com.my.controller.controller.Controller;
import java.awt.event.KeyEvent;
import javax.swing.JTextField;

public class Title extends JTextField implements Component {

  private Controller controller;

  @Override
  public void setMediator(Controller controller) {
    this.controller = controller;
  }

  @Override
  protected void processComponentKeyEvent(KeyEvent keyEvent) {
    controller.markNote();
  }

  @Override
  public String getName() {
    return "Title";
  }
}
