package com.my.chain_of_responibility.handlers;

public class ThrottlingHandler extends BaseHandler {

  private final int requestPerMinute;
  private int request;
  private long currentTime;

  public ThrottlingHandler(int requestPerMinute) {
    this.requestPerMinute = requestPerMinute;
    this.currentTime = System.currentTimeMillis();
  }

  @Override
  public boolean handle(String email, String password) {
    if (System.currentTimeMillis() > currentTime + 60_000) {
      request = 0;
      currentTime = System.currentTimeMillis();
    }

    request++;

    if (request > requestPerMinute) {
      System.out.println("Request limit exceeded!");
      Thread.currentThread().stop();
    }
    return handleNext(email, password);
  }
}
