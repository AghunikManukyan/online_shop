package com.example.online_shop.rest;


import com.example.online_shop.dto.JwtAuthRequestDto;
import com.example.online_shop.dto.JwtAuthResponseDto;
import com.example.online_shop.model.Address;
import com.example.online_shop.model.Product;
import com.example.online_shop.model.User;
import com.example.online_shop.security.CurrentUser;
import com.example.online_shop.service.AddressService;
import com.example.online_shop.service.EmailService;
import com.example.online_shop.service.ProductService;
import com.example.online_shop.service.UserService;
import com.example.online_shop.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class UserEndpoint {

    @Value("${image.upload.dir}")
    private String imageUploadDir;
    @Value("${user.image.upload.dir}")
    private String userImageUploadDir;

    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final PasswordEncoder passwordEncoder;
    private final AddressService addressService;
    private final EmailService emailService;
    private final ProductService productService;


    @PostMapping("/addUser")
    public ResponseEntity addUser(@Valid @RequestBody User user) throws IOException {
        if (userService.findByEmail(user.getEmail()) != null) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .build();
        }


//        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
//        File picture = new File(userImageUploadDir + File.separator + fileName);
//        file.transferTo(picture);
//        user.setPicUrl(fileName);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setToken(UUID.randomUUID().toString());
        userService.save(user);

        String link = "http://localhost:8080/activate/{email}" + user.getEmail()
                + "/{token}" + user.getToken();
        emailService.sendSimpleMessage(user.getEmail(), "Welcome ", "Dear  " + user.getName() + "  you have successfully registered.Please activate your account by clicking on link  " + link);
        return ResponseEntity.ok(user);


    }

    @PostMapping("/auth")
    public ResponseEntity auth(@RequestBody JwtAuthRequestDto authRequestDto) {
        String email = authRequestDto.getEmail();
        User byEmail = userService.findByEmail(email);
        if (byEmail != null) {
            User user = byEmail;
            if (passwordEncoder.matches(authRequestDto.getPassword(), user.getPassword())) {
                String token = jwtTokenUtil.generateToken(user.getEmail());
                JwtAuthResponseDto response = JwtAuthResponseDto.builder()
                        .token(token)
                        .user(user)
                        .build();
                return ResponseEntity.ok(response);
            }
        }
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .build();
    }

    @GetMapping("/activate/{email}/{token}")
    public ResponseEntity userActivate(@PathVariable String email, @PathVariable String token) {
        User byEmail = userService.findByEmail(email);
        if (byEmail != null) {
            User user = byEmail;
            if (user.getToken().equals(token)) {
                user.setActive(true);
                user.setToken("");
                userService.save(user);
                return ResponseEntity.ok(user);
            }
        }
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .build();
    }


    @PostMapping("/user/addAddress")
    public ResponseEntity addAddress(@RequestBody Address address,
                                     @AuthenticationPrincipal CurrentUser currentUser) {

        User one = userService.findById(currentUser.getUser().getId());
        if (one == null) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .build();
        }

        address.setUser(one);
        addressService.save(address);
        return ResponseEntity
                .ok(address);

    }

    @GetMapping("/admin/getUserById/{id}")
    public ResponseEntity findUserById(@PathVariable("id") int id) {
        User byId = userService.findById(id);
        if (byId != null) {
            return ResponseEntity.ok(byId);
        }
        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/admin/userDelete/{id}")
    public ResponseEntity deleteById(@PathVariable("id") int id) {
        User byId = userService.findById(id);
        if (byId != null) {
            userService.deleteById(id);
            return ResponseEntity
                    .ok()
                    .build();
        }
        return ResponseEntity.notFound().build();
    }


    @GetMapping("/admin/allUser")
    public ResponseEntity getAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/user/account")
    public ResponseEntity userAccount(@AuthenticationPrincipal CurrentUser springUser) {
        return ResponseEntity
                .ok(springUser.getUser());

    }

    @GetMapping("/user/accountAddress")
    public ResponseEntity userAccountAddress(@AuthenticationPrincipal CurrentUser springUser) {
        Address addressByUser = addressService.findAddressByUser(springUser.getUser());
        return ResponseEntity
                .ok(addressByUser);

    }

    @PostMapping("/admin/addProduct")
    public ResponseEntity addProduct(@Valid @RequestBody Product product) throws IOException {

//        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
//        File picture = new File(imageUploadDir + File.separator + fileName);
//        file.transferTo(picture);
//        product.setPicUrl(fileName);
        product.setDate(new Date());
        productService.save(product);
        return ResponseEntity.ok(product);

    }


    @PostMapping("/user/updatePassword")
    public ResponseEntity updatePassword(@RequestBody Map<String, String> password,
                                         @AuthenticationPrincipal CurrentUser currentUser) {

        User one = userService.findById(currentUser.getUser().getId());
        if (one == null) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .build();
        }

        if (passwordEncoder.matches(password.get("oldPassword"),one.getPassword())) {
            one.setPassword(passwordEncoder.encode(password.get("newPassword")));
            userService.save(one);
        }

        return ResponseEntity
                .ok(one);
    }




}
