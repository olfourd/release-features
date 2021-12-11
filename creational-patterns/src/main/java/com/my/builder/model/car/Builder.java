package com.my.builder.model.car;

import com.my.builder.model.components.Engine;
import com.my.builder.model.components.GPSNavigator;
import com.my.builder.model.components.Transmission;
import com.my.builder.model.components.TripComputer;

public interface Builder {
  Builder carType(CarType type);
  Builder seats(int seats);
  Builder engine(Engine engine);
  Builder transmission(Transmission transmission);
  Builder tripComputer(TripComputer tripComputer);
  Builder gpsNavigator(GPSNavigator gpsNavigator);
}
