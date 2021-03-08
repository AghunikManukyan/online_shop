package com.example.online_shop.model;



import com.example.online_shop.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "product_order")
public class ProductOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private Date date;

    @Column(name = "status_order")
    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING;

    @Column
    private double price;

    @ManyToOne
    private User user;

    @ManyToMany()
    @JoinTable(name = "order_products",
            joinColumns = @JoinColumn(name = "product_order_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "products_id", referencedColumnName = "id"))
    private List<Products> products;
}


