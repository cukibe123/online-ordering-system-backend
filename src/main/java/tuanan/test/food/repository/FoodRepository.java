package tuanan.test.food.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import tuanan.test.food.entity.FoodEntity;

import java.util.List;

public interface FoodRepository extends JpaRepository<FoodEntity, Long>{
    void deleteByCategoryIdIn(List<Long> categoryIds);
}
