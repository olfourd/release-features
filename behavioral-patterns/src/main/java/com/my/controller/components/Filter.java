package com.my.controller.components;

import com.my.controller.controller.Controller;
import com.my.controller.model.Note;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JTextField;
import javax.swing.ListModel;

public class Filter extends JTextField implements Component {

  private Controller controller;
  private ListModel listModel;

  public Filter() {
  }

  @Override
  public void setMediator(Controller controller) {
    this.controller = controller;
  }

  @Override
  protected void processComponentKeyEvent(KeyEvent keyEvent) {
    String start = getText();
    searchElements(start);
  }

  public void setList(ListModel listModel) {
    this.listModel = listModel;
  }

  private void searchElements(String s) {
    if (listModel == null) {
      return;
    }

    if (s.equals("")) {
      controller.setElementsList(listModel);
      return;
    }

    ArrayList<Note> notes = new ArrayList<>();
    for (int i = 0; i < listModel.getSize(); i++) {
      notes.add((Note) listModel.getElementAt(i));
    }
    DefaultListModel<Note> listModel = new DefaultListModel<>();
    for (Note note : notes) {
      if (note.getName().contains(s)) {
        listModel.addElement(note);
      }
    }
    controller.setElementsList(listModel);
  }

  @Override
  public String getName() {
    return "Filter";
  }
}
