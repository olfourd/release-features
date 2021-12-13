package com.my.controller;

import com.my.controller.components.AddButton;
import com.my.controller.components.DeleteButton;
import com.my.controller.components.Filter;
import com.my.controller.components.List;
import com.my.controller.components.SaveButton;
import com.my.controller.components.TextBox;
import com.my.controller.components.Title;
import com.my.controller.controller.Controller;
import com.my.controller.controller.Editor;
import javax.swing.DefaultListModel;

public class Client {

  public static void main(String[] args) {
    Controller controller = new Editor();

    controller.registerComponent(new Title());
    controller.registerComponent(new TextBox());
    controller.registerComponent(new AddButton());
    controller.registerComponent(new DeleteButton());
    controller.registerComponent(new SaveButton());
    controller.registerComponent(new List(new DefaultListModel()));
    controller.registerComponent(new Filter());

    controller.createGUI();
  }
}
