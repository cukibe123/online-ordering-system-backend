package tuanan.test.food.dto;


import java.util.List;

public record FoodDeleteResponse(
        List<Long> deletedIds
) {
}
