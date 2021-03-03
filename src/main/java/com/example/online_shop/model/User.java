package com.example.online_shop.model;


import com.example.online_shop.model.enums.Gender;
import com.example.online_shop.model.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private int age;

    @Column
    private boolean isActive = false;

    @Column(name = "user_type")
    @Enumerated(value = EnumType.STRING)
    private UserType userType = UserType.USER;

    @Column
    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    private String token;

    @Column(name = "pic_url")
    private String picUrl;


}
