package com.example.online_shop.service;


import com.example.online_shop.model.Menu;

import java.util.List;


public interface MenuService {

    void save(Menu menu);

    List<Menu> findAllMenu();

    Menu menuById(int id);

    List<Menu> findALLByCategoryId(int categoryId);


}
