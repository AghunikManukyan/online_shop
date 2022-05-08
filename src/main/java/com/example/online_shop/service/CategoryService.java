package com.example.online_shop.service;


import com.example.online_shop.model.Category;

import java.util.List;


public interface CategoryService {


    void save(Category category);

    void delete(Category category);

    void deleteById(int categoryId);

    List<Category> findAllCategory();

    Category findCategoryById(int id);


}
