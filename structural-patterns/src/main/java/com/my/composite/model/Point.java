package com.my.composite.model;

import java.awt.Color;
import java.awt.Graphics;

public class Point extends BaseShape{

  private final int POINT_SIZE = 3;

  public Point(int x, int y, Color color) {
    super(x, y, color);
  }

  @Override
  public int getWidth() {
    return POINT_SIZE;
  }

  @Override
  public int getHeight() {
    return POINT_SIZE;
  }

  @Override
  public void paint(Graphics graphics) {
    super.paint(graphics);
    graphics.fillRect(x - 1, y - 1, getWidth(), getHeight());
  }
}
