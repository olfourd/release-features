package com.my.prototype;

import java.util.ArrayList;
import java.util.List;

public class Client {

  public static void main(String[] args) {
    List<Shape> shapes = new ArrayList<>();
    List<Shape> shapesCopy = new ArrayList<>();

    shapes.add(
        Shape.Circle.builder()
            .x(10)
            .y(20)
            .radius(15)
            .color("red")
            .build()
    );

    shapes.add(
        Shape.Rectangle.builder()
            .width(10)
            .height(20)
            .color("blue")
            .build()
    );

    cloneAndCompare(shapes, shapesCopy);
  }

  private static void cloneAndCompare(List<Shape> shapes, List<Shape> shapesCopy) {
    for (Shape shape : shapes) {
      shapesCopy.add(shape.clone());
    }

    for (int i = 0; i < shapes.size(); i++) {
      if (shapes.get(i) != shapesCopy.get(i)) {
        System.out.println(i + ": Shapes are different objects (yay!)");
        if (shapes.get(i).equals(shapesCopy.get(i))) {
          System.out.println(i + ": And they are identical (yay!)");
        } else {
          System.out.println(i + ": But they are not identical (booo!)");
        }
      } else {
        System.out.println(i + ": Shape objects are the same (booo!)");
      }
    }
  }
}
