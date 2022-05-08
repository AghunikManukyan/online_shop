package com.example.online_shop.rest;


import com.example.online_shop.model.Color;
import com.example.online_shop.model.Memory;
import com.example.online_shop.model.Product;
import com.example.online_shop.service.ColorService;
import com.example.online_shop.service.MemoryService;
import com.example.online_shop.service.MenuService;
import com.example.online_shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductEndpoint {


    private final ProductService productService;
    private final ColorService colorService;
    private final MemoryService memoryService;
    private final MenuService menuService;


    @GetMapping("/productById/{id}")
    public ResponseEntity findProductById(@PathVariable("id") int id) {
        Product one = productService.findProductById(id);
        if (one != null) {
            return ResponseEntity
                    .ok(one);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/productsByKeyword/{keyword}")
    public ResponseEntity findProductBySearch(@PathVariable("keyword") String keyword) {
        List<Product> products = productService.listSearch(keyword);
        if (products.size() != 0) {
            return ResponseEntity
                    .ok(products);
        }
        return ResponseEntity.notFound().build();
    }

//    @GetMapping("/getAllProducts")
//    public ResponseEntity getAllProducts() {
//
//        return ResponseEntity.ok(productService.get());
//
//    }

    @GetMapping("/getAllProducts")
    public ResponseEntity<List<Product>> getAllProducts(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "2") Integer pageSize) {
        List<Product> list = productService.findAllProducts(pageNo, pageSize);

        return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/productByMenuId/{id}")
    public ResponseEntity findProductsByMenuId(@PathVariable("id") int id) {
        List<Product> allByMenuId = productService.findAllByMenuId(id);
        if (allByMenuId != null) {
            return ResponseEntity
                    .ok(allByMenuId);
        }
        return ResponseEntity.notFound().build();

    }

    @GetMapping("/productByCategoryId/{id}")
    public ResponseEntity findProductsByCategoryId(@PathVariable("id") int id) {

        List<Product> allByMenuList = productService.findAllByMenuList(menuService.findALLByCategoryId(id));
        if (allByMenuList != null) {
            return ResponseEntity
                    .ok(allByMenuList);
        }
        return ResponseEntity.notFound().build();

    }


    @GetMapping("/productByPrice/{minPrice}/{maxPrice}")
    public ResponseEntity filterProductsByPrice(@PathVariable("minPrice") double minPrice, @PathVariable("maxPrice") double maxPrice) {


        List<Product> allByPrice = productService.filterProductsByPrice(minPrice, maxPrice);

        return ResponseEntity
                .ok(allByPrice);


    }

    @GetMapping("/productByColor/{colorId}")
    public ResponseEntity filterProductsByColor(@PathVariable("colorId") int colorId) {

        List<Product> allByColor = productService.filterProductsByColor(colorId);

        return ResponseEntity
                .ok(allByColor);

    }

    @GetMapping("/productByMemory/{colorId}")
    public ResponseEntity filterProductsByMemory(@PathVariable("memoryId") int memoryId) {


        List<Product> allByMemory = productService.filterProductsByMemory(memoryId);

        return ResponseEntity
                .ok(allByMemory);


    }

    @GetMapping("/productByPriceAndColor/{minPrice}/{maxPrice}/{colorId}")
    public ResponseEntity findProductsByPriceAndColor(@PathVariable("minPrice") double minPrice,
                                                      @PathVariable("maxPrice") double maxPrice,
                                                      @PathVariable("colorId") int colorId) {


        List<Product> allByPriceAndColor = productService.filterProductsByPriceAndColor(minPrice, maxPrice, colorId);

        return ResponseEntity
                .ok(allByPriceAndColor);


    }

    @GetMapping("/productByPriceAndMemory/{minPrice}/{maxPrice}/{memoryId}")
    public ResponseEntity findProductsByPriceAndMemory(@PathVariable("minPrice") double minPrice,
                                                       @PathVariable("maxPrice") double maxPrice,
                                                       @PathVariable("memoryId") int memoryId) {


        List<Product> allByPriceAndMemory =
                productService.filterProductsByPriceAndMemory(minPrice, maxPrice, memoryId);

        return ResponseEntity
                .ok(allByPriceAndMemory);

    }

    @GetMapping("/productByPriceAndColorAndMemory/{minPrice}/{maxPrice}/{colorId}/{memoryId}")
    public ResponseEntity findProductsByPriceAndColorAndMemory(@PathVariable("minPrice") double minPrice,
                                                               @PathVariable("maxPrice") double maxPrice,
                                                               @PathVariable("colorId") int colorId,
                                                               @PathVariable("memoryId") int memoryId
    ) {


        List<Product> allByPriceAndColorAndMemory =
                productService.filterProductsByPriceAndColorAndMemory(minPrice, maxPrice, colorId, memoryId);

        return ResponseEntity
                .ok(allByPriceAndColorAndMemory);

    }

    @GetMapping("/productByColorAndMemory/{colorId}/{memoryId}")
    public ResponseEntity findProductsByColorAndMemory(
            @PathVariable("colorId") int colorId,
            @PathVariable("memoryId") int memoryId
    ) {

        List<Product> allByColorAndMemory =
                productService.filterProductsByColorAndMemory(colorId, memoryId);

        return ResponseEntity
                .ok(allByColorAndMemory);
    }


    @PostMapping("/color/add")
    public ResponseEntity addColor(@RequestBody Color color) {

        colorService.save(color);
        return ResponseEntity.ok(color);
    }

    @PostMapping("/memory/add")
    public ResponseEntity addMemory(@RequestBody Memory memory) {

        memoryService.save(memory);
        return ResponseEntity.ok(memory);
    }
}
