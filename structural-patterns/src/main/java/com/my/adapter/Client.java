package com.my.adapter;

import com.my.adapter.round.RoundHole;
import com.my.adapter.round.RoundPeg;
import com.my.adapter.square.SquarePeg;
import com.my.adapter.square.SquareToRoundPegAdapter;

public class Client {

  public static void main(String[] args) {
    RoundHole hole = new RoundHole(5);
    RoundPeg rpeg = new RoundPeg(5);
    if (hole.fits(rpeg)) {
      System.out.println("Round peg r5 fits round hole r5.");
    }

    SquarePeg smallSqPeg = new SquarePeg(2);
    SquarePeg largeSqPeg = new SquarePeg(20);
    // hole.fits(smallSqPeg); // Не скомпилируется.

    SquareToRoundPegAdapter smallSqPegAdapter = new SquareToRoundPegAdapter(smallSqPeg);
    SquareToRoundPegAdapter largeSqPegAdapter = new SquareToRoundPegAdapter(largeSqPeg);
    if (hole.fits(smallSqPegAdapter)) {
      System.out.println("Square peg w2 fits round hole r5.");
    }
    if (!hole.fits(largeSqPegAdapter)) {
      System.out.println("Square peg w20 does not fit into round hole r5.");
    }
  }
}
