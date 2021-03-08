package com.example.online_shop.repository;


import com.example.online_shop.model.ProductOrder;
import com.example.online_shop.model.User;
import com.example.online_shop.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductOrderRepository extends JpaRepository<ProductOrder, Integer> {
    List<ProductOrder> findAllByStatus(Status status);

    List<ProductOrder> findAllByUser(User user);

}
