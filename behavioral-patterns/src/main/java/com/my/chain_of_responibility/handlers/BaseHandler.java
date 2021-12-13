package com.my.chain_of_responibility.handlers;

public abstract class BaseHandler {

  private BaseHandler next;

  public BaseHandler linkWith(BaseHandler next) {
    this.next = next;
    return next;
  }

  public abstract boolean handle(String email, String password);

  protected boolean handleNext(String email, String password) {
    return next == null || next.handle(email, password);
  }
}
