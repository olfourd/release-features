package com.my.controller.components;

import com.my.controller.controller.Controller;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class DeleteButton extends JButton implements Component {

  private Controller controller;

  public DeleteButton() {
    super("Del");
  }

  @Override
  public void setMediator(Controller controller) {
    this.controller = controller;
  }

  @Override
  protected void fireActionPerformed(ActionEvent actionEvent) {
    controller.deleteNote();
  }

  @Override
  public String getName() {
    return "DelButton";
  }
}
