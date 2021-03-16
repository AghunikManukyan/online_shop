package com.example.online_shop.repository;


import com.example.online_shop.model.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ColorRepository extends JpaRepository<Color, Integer> {

    Color findByColor(String color);
}
