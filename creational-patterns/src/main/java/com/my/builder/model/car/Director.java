package com.my.builder.model.car;

import com.my.builder.model.components.Engine;
import com.my.builder.model.components.GPSNavigator;
import com.my.builder.model.components.Transmission;
import com.my.builder.model.components.TripComputer;

public class Director {

  public void constructSportsCar(Builder builder) {
    builder.carType(CarType.SPORTS_CAR);
    builder.seats(2);
    builder.engine(new Engine(3.0, 0));
    builder.transmission(Transmission.SEMI_AUTOMATIC);
    builder.tripComputer(new TripComputer());
    builder.gpsNavigator(new GPSNavigator());
  }

  public void constructCityCar(Builder builder) {
    builder.carType(CarType.CITY_CAR);
    builder.seats(2);
    builder.engine(new Engine(1.2, 0));
    builder.transmission(Transmission.AUTOMATIC);
    builder.tripComputer(new TripComputer());
    builder.gpsNavigator(new GPSNavigator());
  }

  public void constructSUV(Builder builder) {
    builder.carType(CarType.SUV);
    builder.seats(4);
    builder.engine(new Engine(2.5, 0));
    builder.transmission(Transmission.MANUAL);
    builder.gpsNavigator(new GPSNavigator());
  }
}
