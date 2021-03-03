package com.example.online_shop.service;


import com.example.online_shop.model.Color;
import com.example.online_shop.model.Memory;
import com.example.online_shop.model.Menu;
import com.example.online_shop.model.Product;
import com.example.online_shop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {


    private final ProductRepository productRepository;
    private final MemoryService memoryService;
    private final ColorService colorService;


    public void save(Product product) {
        productRepository.save(product);
    }


    public void deleteById(int id) {
        productRepository.deleteById(id);
    }


    public Product findProductById(int id) {
        Optional<Product> byId = productRepository.findById(id);
        if (byId.isPresent()) {

            return byId.get();
        }
        return null;
    }


    public List<Product> findAll() {
        return productRepository.findAll();
    }


    public List<Product> listSearch(String keyword) {
        if (keyword != null) {
            List<Product> search = productRepository.search(keyword);
            return search;
        }
        return productRepository.findAll();
    }

    public List<Product> findAllProducts(Integer pageNo, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Product> pagedResult = productRepository.findAll(pageable);
        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Product>();
        }
    }


    public List<Product> findAllByMenuId(int id) {
        return productRepository.findAllByMenuId(id);
    }

    public List<Product> findAllByMenuList(List<Menu> menus) {
        List<Product> products = new ArrayList<>();
        for (Menu menu : menus) {
            for (Product product : findAllByMenuId(menu.getId())) {
                products.add(product);

            }

        }

        return products;
    }

    public List<Product> filterProductsByPrice(double minPrice, double maxPrice) {
        return productRepository.filterByPrice(minPrice, maxPrice);
    }


    public List<Product> filterProductsByPriceAndColor(double minPrice, double maxPrice, int colorId) {


        return productRepository.filterByPriceAndColor(minPrice, maxPrice, colorId);


    }

    public List<Product> filterProductsByPriceAndMemory(double minPrice, double maxPrice, int memoryId) {

        return productRepository.filterByPriceAndMemory(minPrice, maxPrice, memoryId);
    }

    public List<Product> filterProductsByColorAndMemory(
            int colorId,
            int memoryId) {
        Color colorById = colorService.findColorById(colorId);
        Memory memoryById = memoryService.findMemoryById(memoryId);

        List<Product> allByColorsAndMemories = productRepository.findAllByColorsAndMemories(colorById, memoryById);


        return allByColorsAndMemories;
    }

    public List<Product> filterProductsByColor(
            int colorId) {
        Color colorById = colorService.findColorById(colorId);


        List<Product> allByColors = productRepository.findAllByColors(colorById);


        return allByColors;
    }

    public List<Product> filterProductsByMemory(
            int memoryId) {
        Memory memoryById = memoryService.findMemoryById(memoryId);


        List<Product> allByMemories = productRepository.findAllByMemories(memoryById);


        return allByMemories;
    }

    public List<Product> filterProductsByPriceAndColorAndMemory(double minPrice,
                                                                double maxPrice,
                                                                int colorId,
                                                                int memoryId) {

        return productRepository.filterByPriceAndColorAndMemory(minPrice, maxPrice, colorId, memoryId);
    }

}
