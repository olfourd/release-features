package com.my.controller.components;

import com.my.controller.controller.Controller;

public interface Component {

  void setMediator(Controller controller);

  String getName();
}
