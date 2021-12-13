package com.my.command.commands;

import com.my.command.Editor;

public class CopyCommand extends Command {

  public CopyCommand(Editor editor) {
    super(editor);
  }

  @Override
  public boolean execute() {
    editor.clipboard = editor.textField.getSelectedText();
    return false;
  }
}
