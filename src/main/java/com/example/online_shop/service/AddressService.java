package com.example.online_shop.service;

import com.example.online_shop.model.Address;
import com.example.online_shop.model.User;




public interface AddressService {

    void save(Address address);
    Address findAddressByUser(User user);


}
