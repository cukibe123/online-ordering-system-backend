package tuanan.test.food.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import tuanan.test.category.dto.CategoryResponse;
import tuanan.test.category.entity.CategoryEntity;
import tuanan.test.category.repository.CategoryRepository;
import tuanan.test.food.dto.FoodDeleteRequest;
import tuanan.test.food.dto.FoodRequest;
import tuanan.test.food.dto.FoodResponse;
import tuanan.test.food.dto.FoodDeleteResponse;
import tuanan.test.food.entity.FoodEntity;
import tuanan.test.food.repository.FoodRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class FoodService {
    private FoodRepository foodRepository;

    private CategoryRepository categoryRepository;

    public FoodService(FoodRepository foodRepository, CategoryRepository categoryRepository) {
        this.foodRepository = foodRepository;
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public FoodResponse createFood(FoodRequest request) {

        FoodEntity entity = new FoodEntity();
        CategoryEntity category = categoryRepository.findById(request.categoryId())
                .orElseThrow(() -> new IllegalArgumentException("Category not found: " + request.categoryId()));

        entity.setName(request.name());
        entity.setDescription(request.description());
        entity.setImageUrl(request.imageUrl());
        entity.setPrice(request.price());
        entity.setCategory(category);

        category.addFood(entity);

        foodRepository.save(entity);
        return new FoodResponse(entity.getId(), entity.getName(), entity.getDescription(),
                entity.getPrice(), entity.getImageUrl(), entity.isAvailable(),
                new CategoryResponse(category.getId(), category.getName()));
    }

    @Transactional
    public FoodDeleteResponse deleteFood(FoodDeleteRequest request) {
        List<Long> ids = request.ids();
        if (ids == null || ids.isEmpty()) {
            throw new IllegalArgumentException("ids must not be empty");
        }

        List<FoodEntity> foods = foodRepository.findAllById(request.ids());

        if (foods.size() != ids.size()) {
            var existing = foods.stream().map(FoodEntity::getId).collect(java.util.stream.Collectors.toSet());
            List<Long> missing = ids.stream().filter(id -> !existing.contains(id)).toList();
            throw new EntityNotFoundException("Foods not found: " + missing);
        }

        foodRepository.deleteAllInBatch(foods);
        return new FoodDeleteResponse(ids);
    }

    public List<FoodEntity> getAllFood() {
        return foodRepository.findAll();
    }
}
