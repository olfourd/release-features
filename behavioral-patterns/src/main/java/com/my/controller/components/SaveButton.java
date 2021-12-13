package com.my.controller.components;

import com.my.controller.controller.Controller;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class SaveButton extends JButton implements Component {

  private Controller controller;

  public SaveButton() {
    super("Save");
  }

  @Override
  public void setMediator(Controller controller) {
    this.controller = controller;
  }

  @Override
  protected void fireActionPerformed(ActionEvent actionEvent) {
    controller.saveChanges();
  }

  @Override
  public String getName() {
    return "SaveButton";
  }
}
