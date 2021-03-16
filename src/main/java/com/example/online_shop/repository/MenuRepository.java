package com.example.online_shop.repository;



import com.example.online_shop.model.Category;
import com.example.online_shop.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {

    Menu findByName(String name);

    List<Menu> findAllByCategories(Category category);

}
