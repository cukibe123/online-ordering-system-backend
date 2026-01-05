package tuanan.test.food.dto;

import java.math.BigDecimal;
import jakarta.validation.constraints.*;

public record FoodRequest (
        @NotBlank
        @Size(max = 150)
        String name,

        @Size(max = 150)
        String description,

        @NotNull
        @DecimalMin(value = "0.01")
        @Digits(integer = 8, fraction = 2)
        BigDecimal price,

        @Size(max = 500)
        String imageUrl,

        @NotNull
        Long categoryId
) {
}
