package com.address.addressbook.service;

import com.address.addressbook.RequestBody.AddressBookReq;
import com.address.addressbook.response.AddressResponse;
import com.address.addressbook.response.ServiceResponse;

public interface AddressService {
  AddressResponse addAddress(AddressBookReq addressBookReq, Long userId);

  ServiceResponse deleteAddress(Long addressId);

  AddressResponse editAddress(AddressBookReq addressBookReq, Long userId);
}
