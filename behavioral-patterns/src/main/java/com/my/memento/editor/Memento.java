package com.my.memento.editor;

public record Memento(String backup, Editor editor) {

  public void restore() {
    editor.restore(backup);
  }
}
