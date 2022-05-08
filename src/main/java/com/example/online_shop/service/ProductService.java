package com.example.online_shop.service;


import com.example.online_shop.model.Menu;
import com.example.online_shop.model.Product;

import java.util.List;


public interface ProductService {

    void save(Product product);

    void deleteById(int id);

    Product findProductById(int id);

    List<Product> findAll();

    List<Product> listSearch(String keyword);

    List<Product> findAllProducts(Integer pageNo, Integer pageSize);

    List<Product> findAllByMenuId(int id);

    List<Product> findAllByMenuList(List<Menu> menus);

    List<Product> filterProductsByPrice(double minPrice, double maxPrice);

    List<Product> filterProductsByPriceAndColor(double minPrice, double maxPrice, int colorId);

    List<Product> filterProductsByPriceAndMemory(double minPrice, double maxPrice, int memoryId);

    List<Product> filterProductsByColorAndMemory(int colorId, int memoryId);

     List<Product> filterProductsByColor(int colorId);

    List<Product> filterProductsByMemory(int memoryId);

    List<Product> filterProductsByPriceAndColorAndMemory(double minPrice, double maxPrice,int colorId, int memoryId);

}
