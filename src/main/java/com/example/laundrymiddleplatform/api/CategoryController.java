package com.example.laundrymiddleplatform.api;

import com.example.laundrymiddleplatform.entities.Category;
import com.example.laundrymiddleplatform.response.ApiResponse;
import com.example.laundrymiddleplatform.services.interfaces.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/{Id}")
    public ResponseEntity<ApiResponse> getCategoryId(@PathVariable Long Id) throws Exception {
        Optional<Category> category = categoryService.getCategoryId(Id);
        if(category.isPresent()){
            return ResponseEntity.ok(ApiResponse.builder()
                    .success(true)
                    .message("Get Category Id")
                    .data(category)
                    .build());
        }else{
            return ResponseEntity.ok(ApiResponse.builder()
                    .success(true)
                    .message("Can't find any category with id = " + Id)
                    .build());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createCategory(@RequestBody Category category) {
        Category savedCategory = categoryService.saveCategory(category);
        return ResponseEntity.ok(ApiResponse.builder()
                .success(true)
                .message("Category Created Successfully")
                .data(savedCategory)
                .build());
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(ApiResponse.builder()
                .success(true)
                .message("List of Categories")
                .data(categories)
                .build());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable Long id, @RequestBody Category category) {
        category.setCategoryId(id); // Ensure the ID is set on the category to update
        Category updatedCategory = categoryService.saveCategory(category);
        return ResponseEntity.ok(ApiResponse.builder()
                .success(true)
                .message("Category Updated Successfully")
                .data(updatedCategory)
                .build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok(ApiResponse.builder()
                .success(true)
                .message("Category Deleted Successfully")
                .build());
    }
}
