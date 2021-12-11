package com.my.builder.model.components;

import com.my.builder.model.car.Car;
import lombok.Setter;

public class TripComputer {

  @Setter
  private Car car;

  public void showFuelLevel() {
    System.out.println("Fuel level: " + car.getFuel());
  }

  public void showStatus() {
    if (this.car.getEngine().isStarted()) {
      System.out.println("Car is started");
    } else {
      System.out.println("Car isn't started");
    }
  }
}
