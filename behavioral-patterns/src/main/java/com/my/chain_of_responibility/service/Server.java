package com.my.chain_of_responibility.service;

import com.my.chain_of_responibility.handlers.BaseHandler;
import java.util.HashMap;
import java.util.Map;
import lombok.Setter;

public class Server {

  private Map<String, String> users = new HashMap<>();

  /**
   * Клиент подаёт готовую цепочку в сервер. Это увеличивает гибкость и
   * упрощает тестирование класса сервера.
   */
  @Setter
  private BaseHandler handler;

  /**
   * Сервер получает email и пароль от клиента и запускает проверку
   * авторизации у цепочки.
   */
  public boolean logIn(String email, String password) {
    if (handler.handle(email, password)) {
      System.out.println("Authorization have been successful!");

      // Здесь должен быть какой-то полезный код, работающий для
      // авторизированных пользователей.

      return true;
    }
    return false;
  }

  public void register(String email, String password) {
    users.put(email, password);
  }

  public boolean hasEmail(String email) {
    return users.containsKey(email);
  }

  public boolean isValidPassword(String email, String password) {
    return users.get(email).equals(password);
  }
}
