package com.my.memento;

import com.my.memento.editor.Editor;
import com.my.memento.model.Circle;
import com.my.memento.model.CompoundShape;
import com.my.memento.model.Point;
import com.my.memento.model.Rectangle;
import java.awt.Color;

public class Client {

  public static void main(String[] args) {
    Editor editor = new Editor();

    editor.loadShapes(
        new Circle(10, 10, 10, Color.BLUE),

        new CompoundShape(
            new Circle(110, 110, 50, Color.RED),
            new Point(160, 160, Color.RED)
        ),

        new CompoundShape(
            new Rectangle(250, 250, 100, 100, Color.GREEN),
            new Point(240, 240, Color.GREEN),
            new Point(240, 360, Color.GREEN),
            new Point(360, 360, Color.GREEN),
            new Point(360, 240, Color.GREEN)
        )
    );
  }
}
