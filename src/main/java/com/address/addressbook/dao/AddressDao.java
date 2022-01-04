package com.address.addressbook.dao;

import com.address.addressbook.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressDao extends JpaRepository<Address, Long> {
  @Query("select a from Address a join a.user u where u.id =:user_id")
  List<Address> getAllAddressByUserId(@Param("user_id") Long id);
}
