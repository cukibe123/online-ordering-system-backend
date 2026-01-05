package tuanan.test.food.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tuanan.test.category.dto.CategoryResponse;
import tuanan.test.food.dto.FoodDeleteRequest;
import tuanan.test.food.dto.FoodDeleteResponse;
import tuanan.test.food.dto.FoodRequest;
import tuanan.test.food.dto.FoodResponse;
import tuanan.test.food.entity.FoodEntity;
import tuanan.test.food.service.FoodService;

import java.util.List;


@RestController
@RequestMapping("/api/food")
public class FoodController {

    private FoodService service;
    public FoodController(FoodService service) {
        this.service = service;
    }

    private FoodResponse toFoodResponse(FoodEntity entity) {
        CategoryResponse categoryResponse = new CategoryResponse(entity.getCategory().getId(), entity.getCategory().getName());
        return new FoodResponse(entity.getId(), entity.getName(), entity.getDescription(), entity.getPrice(),
                entity.getImageUrl(), entity.isAvailable(), categoryResponse);
    }

    @PostMapping
    public ResponseEntity<FoodResponse> createFood(@Valid @RequestBody FoodRequest request) {
        FoodResponse foodResponse = service.createFood(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(foodResponse);
    }

    @PostMapping("/delete")
    public ResponseEntity<FoodDeleteResponse> deleteFood(@Valid @RequestBody FoodDeleteRequest request) {
        FoodDeleteResponse response = service.deleteFood(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public List<FoodResponse> getFood() {
        List<FoodEntity> list = service.getAllFood();
        return list.stream().map(this::toFoodResponse).toList();
    }
}