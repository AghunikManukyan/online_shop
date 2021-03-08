package com.example.online_shop.service.impl;

import com.example.online_shop.model.Products;
import com.example.online_shop.model.User;
import com.example.online_shop.repository.ProductsRepository;
import com.example.online_shop.service.ProductsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductsServiceImpl implements ProductsService {

    private final ProductsRepository productsRepository;


    public void save(Products products) {
        productsRepository.save(products);
    }

    public List<Products> findAllByUser(User user) {
        return productsRepository.findAllByUser(user);
    }

    public List<Products> findAllByStatusAndUser(boolean status, User user) {
        return productsRepository.findAllByStatusAndUser(status, user);
    }

    public void deleteById(int id) {
        productsRepository.deleteById(id);
    }

    public void deleteAll(List<Products> products) {
        productsRepository.deleteAll(products);
    }

    public Optional<Products> findById(int id) {
        return productsRepository.findById(id);
    }

}
