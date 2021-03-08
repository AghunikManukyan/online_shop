package com.example.online_shop.service;


import com.example.online_shop.model.Color;


public interface ColorService {


    void save(Color color);

    void findAllCollor();

    Color findColorById(int id);


}
