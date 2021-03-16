package com.example.online_shop.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "products")
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private int count;

    @Column
    private double price;

    @ManyToOne
    private Product product;


    @Column
    private Date date;

    @Column
    private boolean status = false;

    @ManyToOne
    private User user;

    @ManyToOne
    private Color color;

    @ManyToOne
    private Memory memory;
}
