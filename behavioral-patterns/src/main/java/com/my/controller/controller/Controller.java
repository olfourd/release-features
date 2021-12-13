package com.my.controller.controller;

import com.my.controller.components.Component;
import com.my.controller.model.Note;
import javax.swing.ListModel;

public interface Controller {
  void addNewNote(Note note);
  void deleteNote();
  void getInfoFromList(Note note);
  void saveChanges();
  void markNote();
  void clear();
  void sendToFilter(ListModel listModel);
  void setElementsList(ListModel list);
  void registerComponent(Component component);
  void hideElements(boolean flag);
  void createGUI();
}
