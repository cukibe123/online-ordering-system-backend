package tuanan.test.category.controller;

import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tuanan.test.category.dto.CategoryDeleteRequest;
import tuanan.test.category.dto.CategoryDeleteResponse;
import tuanan.test.category.dto.CategoryRequest;
import tuanan.test.category.dto.CategoryResponse;
import tuanan.test.category.entity.CategoryEntity;
import tuanan.test.category.service.CategoryService;

import java.util.List;


@RestController
@RequestMapping("/api/category")
public class CategoryController {

    CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/delete")
    public ResponseEntity<CategoryDeleteResponse> deleteCategories(@RequestBody CategoryDeleteRequest request) {
        CategoryDeleteResponse response = categoryService.deleteCategories(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> createOrder(@Valid @RequestBody CategoryRequest body) {
        CategoryResponse categoryResponse = categoryService.createCategory(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryResponse);
    }

    private CategoryResponse toCategoryResponse(CategoryEntity entity) {
        return new CategoryResponse(entity.getId(), entity.getName());
    }

    @GetMapping
    public List<CategoryResponse> get() {
        List<CategoryEntity> list = categoryService.getAllCategories();
        return list.stream().map(this::toCategoryResponse).toList();
    }
}


