package com.my.flyweight.model;

import java.awt.Graphics;

public record Tree(int x, int y, TreeType type) {

  public void draw(Graphics g) {
    type.draw(g, x, y);
  }
}
