package com.example.online_shop.service;


import com.example.online_shop.model.ProductOrder;
import com.example.online_shop.model.User;
import com.example.online_shop.model.enums.Status;

import java.util.List;
import java.util.Optional;


public interface ProductOrderService {


    void save(ProductOrder productOrder);

    List<ProductOrder> findProductOrderByStatus(Status status);

    List<ProductOrder> findAllByUser(User user);

    Optional<ProductOrder> findProductOrderById(int id);

    void deleteById(int id);


}
