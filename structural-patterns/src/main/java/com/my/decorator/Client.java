package com.my.decorator;

import com.my.decorator.model.CompressionDecorator;
import com.my.decorator.model.DataSource;
import com.my.decorator.model.DataSourceDecorator;
import com.my.decorator.model.EncryptionDecorator;
import com.my.decorator.model.FileDataSource;

public class Client {

  public static void main(String[] args) {
    String salaryRecords = "Name,Salary\nJohn Smith,100000\nSteven Jobs,912000";
    DataSourceDecorator encoded =
        new CompressionDecorator(
            new EncryptionDecorator(new FileDataSource("structural-patterns/src/main/resources/OutputDemo.txt")));
    encoded.writeData(salaryRecords);
    DataSource plain = new FileDataSource("structural-patterns/src/main/resources/OutputDemo.txt");

    System.out.println("- Input ----------------");
    System.out.println(salaryRecords);
    System.out.println("- Encoded --------------");
    System.out.println(plain.readData());
    System.out.println("- Decoded --------------");
    System.out.println(encoded.readData());
  }
}
