package com.my.memento.model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import lombok.Getter;
import lombok.Setter;

public abstract class BaseShape implements Shape {

  @Getter
  int x, y;
  private int dx = 0, dy = 0;
  @Getter
  @Setter
  private Color color;
  @Getter
  private boolean selected = false;

  public BaseShape(int x, int y, Color color) {
    this.x = x;
    this.y = y;
    this.color = color;
  }

  @Override
  public int getWidth() {
    return 0;
  }

  @Override
  public int getHeight() {
    return 0;
  }

  @Override
  public void drag() {
    dx = x;
    dy = y;
  }

  @Override
  public void moveTo(int x, int y) {
    this.x = dx + x;
    this.y = dy + y;
  }

  @Override
  public void moveBy(int x, int y) {
    this.x += x;
    this.y += y;
  }

  @Override
  public void drop() {
    this.x = dx;
    this.y = dy;
  }

  @Override
  public boolean isInsideBounds(int x, int y) {
    return x > getX() && x < (getX() + getWidth()) &&
        y > getY() && y < (getY() + getHeight());
  }

  @Override
  public void select() {
    selected = true;
  }

  @Override
  public void unSelect() {
    selected = false;
  }

  void enableSelectionStyle(Graphics graphics) {
    graphics.setColor(Color.LIGHT_GRAY);

    Graphics2D g2 = (Graphics2D) graphics;
    float dash1[] = {2.0f};
    g2.setStroke(
        new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 2.0f, dash1, 0.0f));
  }

  void disableSelectionStyle(Graphics graphics) {
    graphics.setColor(color);
    Graphics2D g2 = (Graphics2D) graphics;
    g2.setStroke(new BasicStroke());
  }

  @Override
  public void paint(Graphics graphics) {
    if (isSelected()) {
      enableSelectionStyle(graphics);
    } else {
      disableSelectionStyle(graphics);
    }
  }
}
