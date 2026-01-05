package tuanan.test.food.dto;

import java.util.List;

public record FoodDeleteRequest(
        List<Long> ids
) {
}
