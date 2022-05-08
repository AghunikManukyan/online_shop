package com.example.online_shop.service;


import com.example.online_shop.model.Products;
import com.example.online_shop.model.User;

import java.util.List;
import java.util.Optional;


public interface ProductsService {


    void save(Products products);

    List<Products> findAllByUser(User user);

    List<Products> findAllByStatusAndUser(boolean status, User user);

    void deleteById(int id);

    void deleteAll(List<Products> products);

    Optional<Products> findById(int id);


}
