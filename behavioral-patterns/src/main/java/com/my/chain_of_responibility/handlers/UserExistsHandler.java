package com.my.chain_of_responibility.handlers;

import com.my.chain_of_responibility.service.Server;

public class UserExistsHandler extends BaseHandler {

  private Server server;

  public UserExistsHandler(Server server) {
    this.server = server;
  }

  @Override
  public boolean handle(String email, String password) {
    if (!server.hasEmail(email)) {
      System.out.println("This email is not registered!");
      return false;
    }
    if (!server.isValidPassword(email, password)) {
      System.out.println("Wrong password!");
      return false;
    }
    return handleNext(email, password);
  }
}
