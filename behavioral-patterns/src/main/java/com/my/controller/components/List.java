package com.my.controller.components;

import com.my.controller.controller.Controller;
import com.my.controller.model.Note;
import javax.swing.DefaultListModel;
import javax.swing.JList;

public class List extends JList implements Component {

  private Controller controller;
  private final DefaultListModel LIST_MODEL;

  public List(DefaultListModel listModel) {
    super(listModel);
    this.LIST_MODEL = listModel;
    setModel(listModel);
    this.setLayoutOrientation(JList.VERTICAL);
    Thread thread = new Thread(new Hide(this));
    thread.start();
  }

  @Override
  public void setMediator(Controller controller) {
    this.controller = controller;
  }

  public void addElement(Note note) {
    LIST_MODEL.addElement(note);
    int index = LIST_MODEL.size() - 1;
    setSelectedIndex(index);
    ensureIndexIsVisible(index);
    controller.sendToFilter(LIST_MODEL);
  }

  public void deleteElement() {
    int index = this.getSelectedIndex();
    try {
      LIST_MODEL.remove(index);
      controller.sendToFilter(LIST_MODEL);
    } catch (ArrayIndexOutOfBoundsException ignored) {
    }
  }

  public Note getCurrentElement() {
    return (Note) getSelectedValue();
  }

  @Override
  public String getName() {
    return "List";
  }

  private class Hide implements Runnable {

    private List list;

    Hide(List list) {
      this.list = list;
    }

    @Override
    public void run() {
      while (true) {
        try {
          Thread.sleep(300);
        } catch (InterruptedException ex) {
          ex.printStackTrace();
        }
        if (list.isSelectionEmpty()) {
          controller.hideElements(true);
        } else {
          controller.hideElements(false);
        }
      }
    }
  }
}
