package com.address.addressbook.exception;

import com.address.addressbook.response.ExceptionResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice("com.address.addressbook")
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalException {

  @ExceptionHandler(value = {AddressBookException.class, Exception.class})
  @ResponseBody
  public ResponseEntity<ExceptionResponse> processCustomValidationError(AddressBookException ex) {
    return ResponseEntity.badRequest().body(new ExceptionResponse(ex.getMessage()));
  }

}
