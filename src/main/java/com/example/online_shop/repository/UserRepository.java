package com.example.online_shop.repository;


import com.example.online_shop.model.User;
import com.example.online_shop.model.enums.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);

    List<User> findUsersByUserType(UserType userType);

}
