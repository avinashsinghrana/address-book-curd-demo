package com.address.addressbook.seviceimpl;

import com.address.addressbook.RequestBody.LoginRequest;
import com.address.addressbook.RequestBody.SingUpRequest;
import com.address.addressbook.dao.AddressDao;
import com.address.addressbook.dao.UserDao;
import com.address.addressbook.entity.Address;
import com.address.addressbook.entity.User;
import com.address.addressbook.exception.AddressBookException;
import com.address.addressbook.response.LoginResponse;
import com.address.addressbook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserDao userDao;

  @Autowired
  private AddressDao addressDao;

  @Override
  public LoginResponse login(LoginRequest loginRequest) {
    User user = userDao.findByUsernameAndPassword(loginRequest.getUserName(), loginRequest.getPassword());
    if (ObjectUtils.isEmpty(user))
      return new LoginResponse("User details not available", "200");
    List<Address> addressList = addressDao.getAllAddressByUserId(user.getId());
    return new LoginResponse("Successful", "200", addressList);
  }

  @Override
  public LoginResponse singup(SingUpRequest singUpRequest) {
    LoginResponse response = validateRequestBody(singUpRequest);
    if (response != null) {
      User user = new User();
      user.setName(singUpRequest.getName());
      user.setUsername(singUpRequest.getEmailId());
      user.setPassword(singUpRequest.getPassword());
      user.setPhoneNumber(singUpRequest.getPhoneNumber());
      user.setAddressList(new ArrayList<>());
      userDao.save(user);
    }
    return response;
  }

  private LoginResponse validateRequestBody(SingUpRequest singUpRequest) {
    User user = userDao.findByUsernameAndPassword(singUpRequest.getEmailId(), singUpRequest.getPassword());
    if (user != null)
      throw new AddressBookException("User Already exists");
    return new LoginResponse("User Created Successfully", "200");
  }
}
