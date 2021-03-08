package com.example.online_shop.service;


import com.example.online_shop.model.User;
import com.example.online_shop.model.enums.UserType;

import java.util.List;

public interface UserService {


    void save(User user);

    void deleteById(int id);

    User findByEmail(String email);

    User findById(int id);

    List<User> findAll();

    List<User> findUserByUserType(UserType userType);


}
