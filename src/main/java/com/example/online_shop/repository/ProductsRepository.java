package com.example.online_shop.repository;


import com.example.online_shop.model.Products;
import com.example.online_shop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<Products, Integer> {
    List<Products> findAllByUser(User user);

    List<Products> findAllByStatusAndUser(boolean status, User user);
}
