package com.example.online_shop.service.impl;


import com.example.online_shop.model.Address;
import com.example.online_shop.model.User;
import com.example.online_shop.repository.AddressRepository;
import com.example.online_shop.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;


    public void save(Address address) {
        addressRepository.save(address);
    }

    public Address findAddressByUser(User user) {
        return addressRepository.findAddressByUser(user);
    }



}
