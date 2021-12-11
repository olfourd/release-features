package com.my.adapter.square;

import com.my.adapter.round.RoundPeg;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SquareToRoundPegAdapter extends RoundPeg {

  private SquarePeg peg;

  @Override
  public double getRadius() {
    return Math.sqrt(Math.pow((peg.getWidth() / 2), 2) * 2);
  }
}
