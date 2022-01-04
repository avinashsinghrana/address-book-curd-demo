package com.address.addressbook.controller;

import com.address.addressbook.RequestBody.AddressBookReq;
import com.address.addressbook.exception.AddressBookException;
import com.address.addressbook.response.AddressResponse;
import com.address.addressbook.response.ServiceResponse;
import com.address.addressbook.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/address")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class AddressController {

  @Autowired
  private AddressService addressService;

  @PostMapping("/add")
  public AddressResponse addAddress(@RequestBody AddressBookReq addressBookReq, @RequestParam Long userId) {
    if (addressBookReq == null || userId == null || userId <= 0)
      throw new AddressBookException("Invalid Request");
    return addressService.addAddress(addressBookReq, userId);
  }

  @PostMapping("/edit")
  public AddressResponse editAddress(@RequestBody AddressBookReq addressBookReq, @RequestParam Long userId) {
    if (addressBookReq == null || addressBookReq.getId() == null || addressBookReq.getId() <= 0 || userId == null || userId <= 0)
      throw new AddressBookException("Invalid Request");
    return addressService.editAddress(addressBookReq, userId);
  }

  @PostMapping("/delete")
  public ServiceResponse deleteAddress(@RequestParam Long addressId) {
    if (addressId == null || addressId <= 0)
      throw new AddressBookException("Invalid Request");
    return addressService.deleteAddress(addressId);
  }
}
