package com.address.addressbook.controller;


import com.address.addressbook.RequestBody.LoginRequest;
import com.address.addressbook.RequestBody.SingUpRequest;
import com.address.addressbook.response.LoginResponse;
import com.address.addressbook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping("/login")
  public LoginResponse login(@RequestBody LoginRequest loginRequest) {
    return userService.login(loginRequest);
  }

  @PostMapping("/register")
  public LoginResponse register(@RequestBody SingUpRequest singUpRequest) {
    return userService.singup(singUpRequest);
  }
}
