package com.example.online_shop.service;


import com.example.online_shop.model.ProductOrder;
import com.example.online_shop.model.User;
import com.example.online_shop.model.enums.Status;
import com.example.online_shop.repository.ProductOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductOrderService {

    private final ProductOrderRepository productOrderRepository;

    public void save(ProductOrder productOrder) {
        productOrderRepository.save(productOrder);
    }

    public List<ProductOrder> findProductOrderByStatus(Status status) {
        return productOrderRepository.findAllByStatus(status);

    }

    public List<ProductOrder> findAllByUser(User user) {
        return productOrderRepository.findAllByUser(user);
    }

    public Optional<ProductOrder> findProductOrderById(int id) {
        return productOrderRepository.findById(id);

    }

    public void deleteById(int id) {
        productOrderRepository.deleteById(id);
    }


}
