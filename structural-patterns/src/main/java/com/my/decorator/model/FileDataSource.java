package com.my.decorator.model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStream;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

@AllArgsConstructor
public class FileDataSource implements DataSource {

  private String name;

  @SneakyThrows
  public void writeData(String data) {
    File file = new File(name);
    try (OutputStream fos = new FileOutputStream(file)) {
      fos.write(data.getBytes(), 0, data.length());
    }
  }

  @Override
  @SneakyThrows
  public String readData() {
    char[] buffer = null;
    File file = new File(name);
    try (FileReader reader = new FileReader(file)) {
      buffer = new char[(int) file.length()];
      reader.read(buffer);
    }
    return new String(buffer);
  }
}
