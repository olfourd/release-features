package com.my.adapter.round;

import lombok.Getter;

@Getter
public record RoundHole(double radius) {

  public boolean fits(RoundPeg peg) {
    return (this.getRadius() >= peg.getRadius());
  }
}
