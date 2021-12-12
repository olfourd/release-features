package com.my.composite;

import com.my.composite.model.Circle;
import com.my.composite.model.CompoundShape;
import com.my.composite.model.Point;
import com.my.composite.model.Rectangle;
import java.awt.Color;

public class Client {

  public static void main(String[] args) {
    ImageEditor editor = new ImageEditor();

    editor.loadShapes(
        new Circle(10, 10, Color.BLUE, 10),

        new CompoundShape(
            new Circle(110, 110, Color.RED, 50),
            new Point(160, 160, Color.RED)
        ),

        new CompoundShape(
            new Rectangle(250, 250, Color.GREEN, 100, 100),
            new Point(240, 240, Color.GREEN),
            new Point(240, 360, Color.GREEN),
            new Point(360, 360, Color.GREEN),
            new Point(360, 240, Color.GREEN)
        )
    );
  }
}
