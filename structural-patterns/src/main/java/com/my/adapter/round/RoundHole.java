package com.my.adapter.round;

public record RoundHole(double radius) {

  public double getRadius() {
    return radius;
  }

  public boolean fits(RoundPeg peg) {
    return (this.getRadius() >= peg.getRadius());
  }
}
