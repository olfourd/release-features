package com.my.builder.model;

import com.my.builder.model.car.Car;
import com.my.builder.model.car.Director;
import com.my.builder.model.car.Manual;

public class Client {

  public static void main(String[] args) {
    Director director = new Director();

    Car.CarBuilder carBuilder = Car.builder();
    director.constructSportsCar(carBuilder);
    Car car = carBuilder.build();
    System.out.println(car.toString());

    Manual.ManualBuilder manualBuilder = Manual.builder();
    director.constructSUV(manualBuilder);
    Manual manual = manualBuilder.build();
    System.out.println(manual.print());
  }
}
