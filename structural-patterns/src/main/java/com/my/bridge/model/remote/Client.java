package com.my.bridge.model.remote;

import com.my.bridge.model.device.Device;
import com.my.bridge.model.device.Radio;
import com.my.bridge.model.device.Tv;

public class Client {

  public static void main(String[] args) {
    testDevice(new Tv());
    testDevice(new Radio());
  }

  public static void testDevice(Device device) {
    System.out.println("Tests with basic remote.");
    BasicRemoteController basicRemote = new BasicRemoteController(device);
    basicRemote.power();
    device.printStatus();

    System.out.println("Tests with advanced remote.");
    AdvancedRemoteController advancedRemote = new AdvancedRemoteController(device);
    advancedRemote.power();
    advancedRemote.mute();
    device.printStatus();
  }
}
