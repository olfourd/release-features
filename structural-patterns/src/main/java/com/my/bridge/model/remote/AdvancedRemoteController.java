package com.my.bridge.model.remote;

import com.my.bridge.model.device.Device;

public class AdvancedRemoteController extends BasicRemoteController {

  public AdvancedRemoteController(Device device) {
    super(device);
  }

  public void mute() {
    System.out.println("Remote: mute");
    device.setVolume(0);
  }

}
