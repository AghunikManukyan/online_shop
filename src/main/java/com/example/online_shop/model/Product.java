package com.example.online_shop.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    @NotBlank(message = "Name is mandatory")
    private String name;

    @Column
    private String description;

    @Column
    @NotNull
    private double price;


    @Column
    // @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date date;

    @ManyToOne
    private Menu menu;

    @Column(name = "pic_url")
    @NotBlank(message = "Image is mandatory")
    private String picUrl;

    @ManyToMany()
    @JoinTable(name = "memory_product",
            joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "memory_id", referencedColumnName = "id"))
    private List<Memory> memories;

    @ManyToMany()
    @JoinTable(name = "color_product",
            joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "color_id", referencedColumnName = "id"))
    private List<Color> colors;

}
