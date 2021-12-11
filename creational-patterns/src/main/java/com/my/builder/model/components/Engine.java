package com.my.builder.model.components;

import lombok.Getter;

@Getter
public class Engine {

  protected final double volume;
  private double mileage;
  private boolean started;

  public Engine(double volume, double mileage) {
    this.volume = volume;
    this.mileage = mileage;
  }

  public void on() {
    started = true;
  }

  public void off() {
    started = false;
  }

  public void go(double mileage) {
    if (started) {
      this.mileage += mileage;
    } else {
      System.out.println("Cannot go(), you must start engine first!");
    }
  }


}
