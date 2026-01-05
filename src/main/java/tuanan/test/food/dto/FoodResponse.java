package tuanan.test.food.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import tuanan.test.category.dto.CategoryResponse;

import java.math.BigDecimal;

public record FoodResponse(
        Long id,
        String name,
        String description,
        BigDecimal price,
        String imageUrl,
        Boolean available,
        CategoryResponse categoryResponse
) {
}
