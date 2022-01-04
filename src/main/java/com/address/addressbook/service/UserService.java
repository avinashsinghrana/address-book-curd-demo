package com.address.addressbook.service;

import com.address.addressbook.RequestBody.LoginRequest;
import com.address.addressbook.RequestBody.SingUpRequest;
import com.address.addressbook.response.LoginResponse;

public interface UserService {

  LoginResponse login(LoginRequest loginRequest);

  LoginResponse singup(SingUpRequest singUpRequest);
}
