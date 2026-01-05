package tuanan.test.category.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tuanan.test.category.dto.CategoryDeleteRequest;
import tuanan.test.category.dto.CategoryDeleteResponse;
import tuanan.test.category.dto.CategoryRequest;
import tuanan.test.category.dto.CategoryResponse;
import tuanan.test.category.entity.CategoryEntity;
import tuanan.test.category.repository.CategoryRepository;
import tuanan.test.food.repository.FoodRepository;

import java.util.List;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    private FoodRepository foodRepository;

    public CategoryService(CategoryRepository categoryRepository, FoodRepository foodRepository) {
        this.categoryRepository = categoryRepository;
        this.foodRepository = foodRepository;
    }

    public List<CategoryEntity> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Transactional
    public CategoryDeleteResponse deleteCategories(CategoryDeleteRequest request) {
        List<Long> ids = request.ids();

        if (ids == null || ids.isEmpty()) {
            throw new IllegalArgumentException("ids must not be empty");
        }

        List<CategoryEntity> categories = categoryRepository.findAllById(ids);

        if (ids.size() != categories.size()) {
            throw new EntityNotFoundException("Some categories are not found");
        }

        foodRepository.deleteByCategoryIdIn(ids);
        categoryRepository.deleteAllInBatch(categories);
        return new CategoryDeleteResponse(ids);
    }

    @Transactional
    public CategoryResponse createCategory(CategoryRequest categoryRequest) {
        CategoryEntity entity = new CategoryEntity();
        entity.setName(categoryRequest.name());
        categoryRepository.save(entity);
        return new CategoryResponse(entity.getId(), entity.getName());
    }
}
