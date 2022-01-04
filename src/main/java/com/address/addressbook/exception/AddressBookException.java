package com.address.addressbook.exception;

public class AddressBookException extends RuntimeException {
  private String message;

  public AddressBookException(String message) {
    super(message);
    this.message = message;
  }

  @Override
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
