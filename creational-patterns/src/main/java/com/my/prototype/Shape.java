package com.my.prototype;

import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@EqualsAndHashCode
public abstract class Shape {

  private int x, y;
  private String color;

  public Shape(Shape shape) {
    if ( shape != null) {
      this.x = shape.x;
      this.y = shape.y;
      this.color = shape.color;
    }
  }

  @Override
  public abstract Shape clone();

  @SuperBuilder
  @EqualsAndHashCode(callSuper = true)
  public static class Circle extends Shape {

    private int radius;

    public Circle(Circle circle) {
      super(circle);
      if (circle != null) {
        this.radius = circle.radius;
      }
    }

    @Override
    public Shape clone() {
      return new Circle(this);
    }
  }

  @SuperBuilder
  @EqualsAndHashCode(callSuper = true)
  public static class Rectangle extends Shape {
    public int width;
    public int height;

    public Rectangle(Rectangle rectangle) {
      super(rectangle);
      if (rectangle != null) {
        this.width = rectangle.width;
        this.height = rectangle.height;
      }
    }

    @Override
    public Shape clone() {
      return new Rectangle(this);
    }
  }
}
