package com.my.builder.model.car;

import com.my.builder.model.components.Engine;
import com.my.builder.model.components.GPSNavigator;
import com.my.builder.model.components.Transmission;
import com.my.builder.model.components.TripComputer;

/**
 * Руководство автомобиля — это второй продукт. Заметьте, что руководство и сам
 * автомобиль не имеют общего родительского класса. По сути, они независимы.
 */
public record Manual(CarType carType, int seats, Engine engine, Transmission transmission,
                     TripComputer tripComputer, GPSNavigator gpsNavigator) {

  public String print() {
    String info = "";
    info += "Type of car: " + carType + "\n";
    info += "Count of seats: " + seats + "\n";
    info += "Engine: volume - " + engine.getVolume() + "; mileage - " + engine.getMileage() + "\n";
    info += "Transmission: " + transmission + "\n";
    if (this.tripComputer != null) {
      info += "Trip Computer: Functional" + "\n";
    } else {
      info += "Trip Computer: N/A" + "\n";
    }
    if (this.gpsNavigator != null) {
      info += "GPS Navigator: Functional" + "\n";
    } else {
      info += "GPS Navigator: N/A" + "\n";
    }
    return info;
  }

  public static ManualBuilder builder() {
    return new ManualBuilder();
  }

  public static class ManualBuilder implements Builder {
    private CarType carType;
    private int seats;
    private Engine engine;
    private Transmission transmission;
    private TripComputer tripComputer;
    private GPSNavigator gpsNavigator;

    public ManualBuilder carType(CarType carType) {
      this.carType = carType;
      return this;
    }

    public ManualBuilder seats(int seats) {
      this.seats = seats;
      return this;
    }

    public ManualBuilder engine(Engine engine) {
      this.engine = engine;
      return this;
    }

    public ManualBuilder transmission(Transmission transmission) {
      this.transmission = transmission;
      return this;
    }

    public ManualBuilder tripComputer(TripComputer tripComputer) {
      this.tripComputer = tripComputer;
      return this;
    }

    public ManualBuilder gpsNavigator(GPSNavigator gpsNavigator) {
      this.gpsNavigator = gpsNavigator;
      return this;
    }

    public Manual build() {
      return new Manual(carType, seats, engine, transmission, tripComputer, gpsNavigator);
    }
  }
}
