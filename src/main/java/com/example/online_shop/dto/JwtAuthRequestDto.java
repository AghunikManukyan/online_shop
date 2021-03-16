package com.example.online_shop.dto;

import lombok.Data;

@Data
public class JwtAuthRequestDto {

    private String email;
    private String password;

}
