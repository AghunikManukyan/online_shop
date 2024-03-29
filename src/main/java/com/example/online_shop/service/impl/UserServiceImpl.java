package com.example.online_shop.service.impl;

import com.example.online_shop.exception.ResourceNotFoundException;
import com.example.online_shop.model.User;
import com.example.online_shop.model.enums.UserType;
import com.example.online_shop.repository.UserRepository;
import com.example.online_shop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;

    public void save(User user) {
        userRepository.save(user);
    }

    public void deleteById(int id) {
        userRepository.deleteById(id);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findById(int id) {
        Optional<User> byId = userRepository.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        }
        throw new ResourceNotFoundException("User not found"+byId.get().getName());
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public List<User> findUserByUserType(UserType userType) {
        return userRepository.findUsersByUserType(userType);
    }

}
