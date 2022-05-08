package com.example.online_shop.repository;


import com.example.online_shop.model.Address;
import com.example.online_shop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

    Address findAddressByUser(User user);
}
