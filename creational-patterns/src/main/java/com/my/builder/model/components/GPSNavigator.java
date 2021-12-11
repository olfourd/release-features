package com.my.builder.model.components;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class GPSNavigator {

  @Getter
  private final String route;

  public GPSNavigator() {
    this.route = "221b, Baker Street, London  to Scotland Yard, 8-10 Broadway, London";
  }
}
