package com.example.online_shop.service.impl;

import com.example.online_shop.model.Menu;
import com.example.online_shop.repository.MenuRepository;
import com.example.online_shop.service.CategoryService;
import com.example.online_shop.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {
    private final MenuRepository menuRepository;
    private final CategoryService categoryService;


    public void save(Menu menu) {

        if (menuRepository.findByName(menu.getName()) == null) {

            menuRepository.save(menu);
        }

    }


    public List<Menu> findAllMenu() {
        return menuRepository.findAll();
    }


    public Menu menuById(int id) {
        Optional<Menu> byId = menuRepository.findById(id);
        if (byId.isPresent()) {

            return byId.get();
        }
        return null;

    }

    public List<Menu> findALLByCategoryId(int categoryId) {

        return menuRepository.findAllByCategories(categoryService.findCategoryById(categoryId));

    }
}
