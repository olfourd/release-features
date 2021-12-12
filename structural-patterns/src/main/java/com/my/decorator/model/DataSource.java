package com.my.decorator.model;

public interface DataSource {

  void writeData(String data);

  String readData();
}
