package com.example.online_shop.rest;



import com.example.online_shop.model.Category;
import com.example.online_shop.model.Menu;
import com.example.online_shop.service.CategoryService;
import com.example.online_shop.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class MainEndpoint {

    private final CategoryService categoryService;
    private final MenuService menuService;

    @PostMapping("categories/add")
    public ResponseEntity addCategory(@RequestBody Category category) {
        categoryService.save(category);
        return ResponseEntity.ok(category);

    }

    @PostMapping("menus/add")
    public ResponseEntity addMenu(@RequestBody Menu menu) {
        menuService.save(menu);
        return ResponseEntity.ok(menu);
    }

    @GetMapping("categories/all")
    public ResponseEntity findAllCategories() {
        return ResponseEntity.ok(categoryService.findAllCategory());
    }


    @GetMapping("menus/all")
    public ResponseEntity findAllMenu() {
        return ResponseEntity.ok(menuService.findAllMenu());
    }


    @GetMapping("menus/byCategory/{categoryId}")
    public ResponseEntity findAllMenuByCategory(@PathVariable("categoryId") int categoryId) {

        return ResponseEntity.ok(menuService.findALLByCategoryId(categoryId));
    }

}
