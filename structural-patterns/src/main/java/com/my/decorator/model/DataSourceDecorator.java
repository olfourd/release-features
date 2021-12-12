package com.my.decorator.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DataSourceDecorator implements DataSource {

  private DataSource wrapped;

  @Override
  public void writeData(String data) {
    wrapped.writeData(data);
  }

  @Override
  public String readData() {
    return wrapped.readData();
  }
}
