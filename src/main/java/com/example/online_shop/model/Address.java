package com.example.online_shop.model;


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
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String country;

    @Column
    private String region;

    @Column
    private String city;

    @Column
    private String street;

    @Column
    private String apartment;

    @Column
    private String phone;

    @ManyToOne
    private User user;

}
