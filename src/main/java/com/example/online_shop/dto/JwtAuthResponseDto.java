package com.example.online_shop.dto;


import com.example.online_shop.model.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtAuthResponseDto {

    private String token;
    private User user;

}
