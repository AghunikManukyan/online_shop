package com.example.online_shop.rest;



import com.example.online_shop.model.Product;
import com.example.online_shop.model.ProductOrder;
import com.example.online_shop.model.Products;
import com.example.online_shop.model.enums.Status;
import com.example.online_shop.security.CurrentUser;
import com.example.online_shop.service.ProductOrderService;
import com.example.online_shop.service.ProductService;
import com.example.online_shop.service.ProductsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ProductOrderEndpoint {


    private final ProductOrderService productOrderService;
    private final ProductsService productsService;
    private final ProductService productService;


    @GetMapping("/user/addOrder")
    private ResponseEntity addOrder(@AuthenticationPrincipal CurrentUser springUser) {

        List<Products> allByUser = productsService.findAllByUser(springUser.getUser());
        if (allByUser == null) {
            return ResponseEntity.notFound().build();
        }
        ProductOrder productOrder = new ProductOrder();
        double price = 0;
        for (Products products : allByUser) {
            products.setStatus(true);
            price += products.getPrice();
        }
        productOrder.setPrice(price);
        productOrder.setDate(new Date());
        productOrder.setProducts(allByUser);
        productOrder.setUser(springUser.getUser());
        productOrderService.save(productOrder);
        return ResponseEntity
                .ok().build();
    }

    @GetMapping("/user/productCartByUser")
    public ResponseEntity productCart(@AuthenticationPrincipal CurrentUser currentUser) {
        List<Products> allByStatusAndUser = productsService.findAllByStatusAndUser(false, currentUser.getUser());
        if (allByStatusAndUser != null) {
            return ResponseEntity
                    .ok(allByStatusAndUser);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/user/addProducts")
    public ResponseEntity addProducts(@RequestBody Products products,
                                      @AuthenticationPrincipal CurrentUser springUser) {

        products.setUser(springUser.getUser());
        Product productById = productService.findProductById(products.getProduct().getId());
        products.setPrice(products.getCount() * productById.getPrice());
        products.setDate(new Date());
        List<Products> allByUser = productsService.findAllByUser(springUser.getUser());
        productsService.save(products);
        for (Products products1 : allByUser) {
            if (products1.getProduct().getId() == products.getProduct().getId()) {
                products.setCount(products.getCount() + products1.getCount());
                productsService.deleteById(products1.getId());
            }

        }

        return ResponseEntity
                .ok(products);
    }


    @GetMapping("/admin/productOrderByPending")
    public ResponseEntity getProductOrderPending() {
        List<ProductOrder> allByStatus = productOrderService.findProductOrderByStatus(Status.PENDING);

        if (allByStatus != null) {
            return ResponseEntity
                    .ok(allByStatus);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/admin/productOrderByCompletes")
    public ResponseEntity getProductOrderCompletes() {
        List<ProductOrder> allByStatus = productOrderService.findProductOrderByStatus(Status.COMPLETES);

        if (allByStatus != null) {
            return ResponseEntity
                    .ok(allByStatus);
        }
        return ResponseEntity.notFound().build();
    }


    @GetMapping("/admin/productOrderByProcessing")
    public ResponseEntity getProductOrderProcessing() {
        List<ProductOrder> allByStatus = productOrderService.findProductOrderByStatus(Status.PROCESSING);

        if (allByStatus != null) {
            return ResponseEntity
                    .ok(allByStatus);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/admin/confirmedOrder/{id}")
    public ResponseEntity confirmedOrder(@PathVariable("id") int id) {
        Optional<ProductOrder> byId = productOrderService.findProductOrderById(id);

        if (byId.isPresent()) {
            byId.get().setStatus(Status.PROCESSING);
            return ResponseEntity
                    .ok(byId);
        }
        return ResponseEntity.notFound().build();
    }


    @GetMapping("/admin/finishOrder/{id}")
    public ResponseEntity finishOrder(@PathVariable("id") int id) {
        Optional<ProductOrder> byId = productOrderService.findProductOrderById(id);

        if (byId.isPresent()) {
            byId.get().setStatus(Status.COMPLETES);
            return ResponseEntity
                    .ok(byId);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/user/deleteOrder")
    public ResponseEntity deleteOrder(@RequestParam("id") int id, @AuthenticationPrincipal CurrentUser springUser) {
        Optional<ProductOrder> one = productOrderService.findProductOrderById(id);
        productsService.deleteAll(one.get().getProducts());
        productOrderService.deleteById(id);

        return ResponseEntity
                .ok()
                .build();
    }

    @GetMapping("/user/myOrder")
    public ResponseEntity myOrder(@AuthenticationPrincipal CurrentUser springUser) {
        List<ProductOrder> allByUser = productOrderService.findAllByUser(springUser.getUser());

        if (allByUser != null) {
            return ResponseEntity
                    .ok(allByUser);
        }
        return ResponseEntity.notFound().build();

    }


}
