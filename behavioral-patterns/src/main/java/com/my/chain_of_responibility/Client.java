package com.my.chain_of_responibility;

import com.my.chain_of_responibility.handlers.BaseHandler;
import com.my.chain_of_responibility.handlers.RoleCheckHandler;
import com.my.chain_of_responibility.handlers.ThrottlingHandler;
import com.my.chain_of_responibility.handlers.UserExistsHandler;
import com.my.chain_of_responibility.service.Server;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Client {

  private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
  private static Server server;

  private static void init() {
    server = new Server();
    server.register("admin@example.com", "admin_pass");
    server.register("user@example.com", "user_pass");

    // Проверки связаны в одну цепь. Клиент может строить различные цепи,
    // используя одни и те же компоненты.
    BaseHandler handler = new ThrottlingHandler(2)
        .linkWith(new UserExistsHandler(server))
        .linkWith(new RoleCheckHandler());

    // Сервер получает цепочку от клиентского кода.
    server.setHandler(handler);
  }

  public static void main(String[] args) {
    server = new Server();
    server.register("admin@example.com", "admin_pass");
    server.register("user@example.com", "user_pass");
  }
}
