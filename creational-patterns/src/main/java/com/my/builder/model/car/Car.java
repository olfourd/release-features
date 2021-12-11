package com.my.builder.model.car;

import com.my.builder.model.components.Engine;
import com.my.builder.model.components.GPSNavigator;
import com.my.builder.model.components.Transmission;
import com.my.builder.model.components.TripComputer;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
public class Car {

  private final CarType carType;
  private final int seats;
  private final Engine engine;
  private final Transmission transmission;
  private final TripComputer tripComputer;
  private final GPSNavigator gpsNavigator;
  @Setter
  private double fuel = 0;

  public Car(CarType carType, int seats, Engine engine, Transmission transmission, TripComputer tripComputer,
             GPSNavigator gpsNavigator) {
    this.carType = carType;
    this.seats = seats;
    this.engine = engine;
    this.transmission = transmission;
    this.tripComputer = tripComputer;
    if (this.tripComputer != null) {
      this.tripComputer.setCar(this);
    }
    this.gpsNavigator = gpsNavigator;
  }

  public static CarBuilder builder() {
    return new CarBuilder();
  }

  public static class CarBuilder implements Builder {
    private CarType carType;
    private int seats;
    private Engine engine;
    private Transmission transmission;
    private TripComputer tripComputer;
    private GPSNavigator gpsNavigator;

    public CarBuilder carType(CarType carType) {
      this.carType = carType;
      return this;
    }

    public CarBuilder seats(int seats) {
      this.seats = seats;
      return this;
    }

    public CarBuilder engine(Engine engine) {
      this.engine = engine;
      return this;
    }

    public CarBuilder transmission(Transmission transmission) {
      this.transmission = transmission;
      return this;
    }

    public CarBuilder tripComputer(TripComputer tripComputer) {
      this.tripComputer = tripComputer;
      return this;
    }

    public CarBuilder gpsNavigator(GPSNavigator gpsNavigator) {
      this.gpsNavigator = gpsNavigator;
      return this;
    }

    public Car build() {
      return new Car(carType, seats, engine, transmission, tripComputer, gpsNavigator);
    }
  }
}
