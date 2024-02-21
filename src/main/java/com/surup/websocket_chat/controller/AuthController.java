package com.surup.websocket_chat.controller;

import com.surup.websocket_chat.dto.request.UserRegisterRequest;
import com.surup.websocket_chat.service.UserAccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class AuthController {

  private final UserAccountService userAccountService;

  @GetMapping("/login")
  public String login() {
    return "login";
  }

  @GetMapping("/signup")
  public String signup() {
    return "signup";
  }

  @PostMapping("/signup")
  public String signup(@Valid UserRegisterRequest userRegisterDto) {
    userAccountService.createUser(userRegisterDto);
    return "login";
  }

}
