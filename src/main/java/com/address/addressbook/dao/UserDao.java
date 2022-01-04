package com.address.addressbook.dao;

import com.address.addressbook.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository public interface UserDao extends JpaRepository<User, Long> {

  User findByUsernameAndPassword(String userName,  String password);
}
