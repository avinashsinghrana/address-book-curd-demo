package com.address.addressbook.response;

public class LoginResponse<T> extends ServiceResponse {

  private T data;

  public LoginResponse(String message, String statusCode, T data) {
    super(message, statusCode);
    this.data = data;
  }

  public LoginResponse(String message, String statusCode) {
    super(message, statusCode);
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }
}
