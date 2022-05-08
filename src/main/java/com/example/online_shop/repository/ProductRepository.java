package com.example.online_shop.repository;



import com.example.online_shop.model.Color;
import com.example.online_shop.model.Memory;
import com.example.online_shop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT p FROM Product p WHERE p.name LIKE %?1%"
            + " OR p.description LIKE %?1%"
            + " OR CONCAT(p.price, '') LIKE %?1%")
    List<Product> search(String keyword);


    @Query(value = "SELECT * FROM Product  WHERE price >= ?1 and price<= ?2", nativeQuery = true)
    List<Product> filterByPrice(@Param("minPrice") Double minPrice, @Param("maxPrice") Double maxPrice);


    @Query(value = "SELECT * FROM product Inner join color_product on color_product.product_id = product.id " +
            "WHERE color_product.color_id = ?3 AND price >= ?1 and price<= ?2  ", nativeQuery = true)
    List<Product> filterByPriceAndColor(@Param("minPrice") Double minPrice, @Param("maxPrice") Double maxPrice,
                                        @Param("colorId") int colorId);


    @Query(value = "SELECT * FROM product Inner join memory_product on memory_product.product_id = product.id " +
            "WHERE memory_product.memory_id = ?3 AND price >= ?1 and price<= ?2  ", nativeQuery = true)
    List<Product> filterByPriceAndMemory(@Param("minPrice") Double minPrice, @Param("maxPrice") Double maxPrice,
                                         @Param("memoryId") int memoryId);

    @Query(value = "SELECT * FROM (( product " +
            "Inner join memory_product on memory_product.product_id = product.id) " +
            "Inner join color_product on color_product.product_id = product.id)" +
            "WHERE color_product.color_id = ?3" +
            " AND memory_product.memory_id = ?4 " +
            "AND price >= ?1 and price<= ?2  ", nativeQuery = true)
    List<Product> filterByPriceAndColorAndMemory(@Param("minPrice") Double minPrice,
                                                 @Param("maxPrice") Double maxPrice,
                                                 @Param("colorId") int colorId,
                                                 @Param("memoryId") int memoryId);

    List<Product> findAllByColorsAndMemories(Color color, Memory memory);

    List<Product> findAllByColors(Color color);

    List<Product> findAllByMemories(Memory memory);

    List<Product> findAllByMenuId(int id);


}
