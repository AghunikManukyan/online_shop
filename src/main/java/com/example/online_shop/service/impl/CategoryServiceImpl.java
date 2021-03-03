package com.example.online_shop.service.impl;

import com.example.online_shop.model.Category;
import com.example.online_shop.repository.CategoryRepository;
import com.example.online_shop.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public void save(Category category) {

        if (categoryRepository.findByName(category.getName()) == null) {
            categoryRepository.save(category);
        }

    }

    public void delete(Category category) {
        categoryRepository.delete(category);
    }

    public void deleteById(int categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    public List<Category> findAllCategory() {
        return categoryRepository.findAll();
    }

    public Category findCategoryById(int id) {
        Optional<Category> byId = categoryRepository.findById(id);
        if (byId.isPresent()) {

            return byId.get();
        }
        return null;
    }


}
