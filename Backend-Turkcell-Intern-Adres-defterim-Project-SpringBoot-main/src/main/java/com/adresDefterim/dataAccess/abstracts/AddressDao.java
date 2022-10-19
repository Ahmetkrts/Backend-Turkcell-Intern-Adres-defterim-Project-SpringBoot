package com.adresDefterim.dataAccess.abstracts;

import com.adresDefterim.entities.concrates.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressDao extends JpaRepository<Address,Long> {

    Address getByAddressId(Long addressId);
    List<Address> getByUserUserName(String userName);
}
