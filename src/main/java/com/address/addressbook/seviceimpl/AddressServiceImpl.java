package com.address.addressbook.seviceimpl;

import com.address.addressbook.RequestBody.AddressBookReq;
import com.address.addressbook.dao.AddressDao;
import com.address.addressbook.dao.UserDao;
import com.address.addressbook.entity.Address;
import com.address.addressbook.entity.User;
import com.address.addressbook.exception.AddressBookException;
import com.address.addressbook.response.AddressResponse;
import com.address.addressbook.response.ServiceResponse;
import com.address.addressbook.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.regex.Pattern;

@Service
public class AddressServiceImpl implements AddressService {

  @Autowired
  private UserDao userDao;

  @Autowired
  private AddressDao addressDao;

  @Override
  @Transactional
  public AddressResponse addAddress(AddressBookReq addressBookReq, Long userId) {
    User user = userDao.getById(userId);
    if (user != null)
      throw new AddressBookException("User not found");
    validateAddressBoolRequest(addressBookReq);
    Address address = new Address();
    address = mapAddressTable(addressBookReq, user, address);
    user.getAddressList().add(address);
    userDao.save(user);
    return buildResponse(address, user.getId());
  }

  @Override
  public ServiceResponse deleteAddress(Long addressId) {
    addressDao.deleteById(addressId);
    ServiceResponse response = new ServiceResponse("Successful", "200");
    return response;
  }

  @Override public AddressResponse editAddress(AddressBookReq addressBookReq, Long userId) {
    User user = userDao.getById(userId);
    if (user != null)
      throw new AddressBookException("User not found");
    validateAddressBoolRequest(addressBookReq);
    user.getAddressList().removeIf(v -> v.getId().equals(addressBookReq.getId()));
    Address address = addressDao.getById(addressBookReq.getId());
    address = mapAddressTable(addressBookReq, user, address);
    user.getAddressList().add(address);
    userDao.save(user);
    return buildResponse(address, user.getId());
  }

  private AddressResponse buildResponse(Address address, Long userId) {
    AddressResponse response = new AddressResponse("Successful", "200");
    response.setAddress1(address.getAddress1());
    response.setAddress2(address.getAddress2());
    response.setName(address.getName());
    response.setCity(address.getCity());
    response.setPhoneNumber(address.getPhoneNumber());
    response.setState(address.getState());
    response.setZip(address.getZip());
    response.setUserId(userId);
    return response;
  }

  private Address mapAddressTable(AddressBookReq addressBookReq, User user, Address address) {
    address.setAddress1(addressBookReq.getAddress1());
    address.setAddress2(addressBookReq.getAddress2());
    address.setName(addressBookReq.getName());
    address.setCity(addressBookReq.getCity());
    address.setPhoneNumber(addressBookReq.getPhoneNumber());
    address.setState(addressBookReq.getState());
    address.setZip(addressBookReq.getZip());
    address.setUser(user);
    return addressDao.save(address);
  }

  private void validateAddressBoolRequest(AddressBookReq addressBookReq) {
    if (!StringUtils.hasText(addressBookReq.getName()))
      throw new AddressBookException("Name is empty, please check request");

    if (!Pattern.matches("^[a-zA-z]{3,}$", addressBookReq.getName()))
      throw new AddressBookException("Invalid name format, please check request");

    if (!StringUtils.hasText(addressBookReq.getAddress1()))
      throw new AddressBookException("Address is empty, please check request");

    if (!StringUtils.hasText(addressBookReq.getCity()))
      throw new AddressBookException("City is empty, please check request");

    if (!StringUtils.hasText(addressBookReq.getState()))
      throw new AddressBookException("State is empty, please check request");

    if (!StringUtils.hasText(addressBookReq.getPhoneNumber()))
      throw new AddressBookException("Phone number is empty, please check request");

    if (!Pattern.matches("^[0|91]?[6789][0-9]{9}$", addressBookReq.getName()))
      throw new AddressBookException("Invalid phone number, please check request");

    if (!StringUtils.hasText(addressBookReq.getZip()))
      throw new AddressBookException("Zip is empty, please check request");

    if (!Pattern.matches("^[1-9][1-9]{5}$", addressBookReq.getZip()))
      throw new AddressBookException("Invalid zip code, please check request");
  }
}
