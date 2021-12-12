package com.my.bridge.model.remote;

import com.my.bridge.model.device.Device;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BasicRemoteController implements RemoteController {

  protected Device device;

  @Override
  public void power() {
    System.out.println("Remote: power toggle");
    if (device.isEnabled()) {
      device.disable();
    } else {
      device.enable();
    }
  }

  @Override
  public void volumeDown() {
    System.out.println("Remote: volume down");
    device.setVolume(device.getVolume() - 10);
  }

  @Override
  public void volumeUp() {
    System.out.println("Remote: volume up");
    device.setVolume(device.getVolume() + 10);
  }

  @Override
  public void channelDown() {
    System.out.println("Remote: channel down");
    device.setChannel(device.getChannel() - 1);
  }

  @Override
  public void channelUp() {
    System.out.println("Remote: channel up");
    device.setChannel(device.getChannel() + 1);
  }

}
