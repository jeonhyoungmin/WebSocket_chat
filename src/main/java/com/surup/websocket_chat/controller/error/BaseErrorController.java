package com.surup.websocket_chat.controller.error;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BaseErrorController implements ErrorController {

  @RequestMapping("/error")
  public String error() {
    return "error";
  }

}
