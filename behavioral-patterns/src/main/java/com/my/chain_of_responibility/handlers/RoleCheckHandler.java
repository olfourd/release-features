package com.my.chain_of_responibility.handlers;

public class RoleCheckHandler extends BaseHandler {

  @Override
  public boolean handle(String email, String password) {
    if (email.equals("admin@example.com")) {
      System.out.println("Hello, admin!");
      return true;
    }
    System.out.println("Hello, user!");
    return handleNext(email, password);
  }
}
