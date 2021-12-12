package com.my.flyweight.model;

import java.awt.Color;
import java.awt.Graphics;

public record TreeType(String name, Color color, String otherTreeData) {

  public void draw(Graphics g, int x, int y) {
    g.setColor(Color.BLACK);
    g.fillRect(x - 1, y, 3, 5);
    g.setColor(color);
    g.fillOval(x - 5, y - 10, 10, 10);
  }
}
