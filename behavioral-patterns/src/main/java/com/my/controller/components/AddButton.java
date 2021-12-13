package com.my.controller.components;

import com.my.controller.controller.Controller;
import com.my.controller.model.Note;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class AddButton extends JButton implements Component {

  private Controller controller;

  public AddButton() {
    super("Add");
  }

  @Override
  public void setMediator(Controller controller) {
    this.controller = controller;
  }

  @Override
  protected void fireActionPerformed(ActionEvent actionEvent) {
    controller.addNewNote(new Note());
  }

  @Override
  public String getName() {
    return "AddButton";
  }
}
