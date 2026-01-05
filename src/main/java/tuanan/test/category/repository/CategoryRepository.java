package tuanan.test.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tuanan.test.category.entity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
}
