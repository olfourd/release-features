package com.my.adapter.square;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SquarePeg {

  private double width;

  public double getSquare() {
    return Math.pow(this.width, 2);
  }
}
