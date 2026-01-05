package tuanan.test.category.entity;


import jakarta.persistence.*;
import tuanan.test.food.entity.FoodEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "category")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(
            mappedBy = "category",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
     )
    private List<FoodEntity> foodList = new ArrayList<>();

    public void addFood(FoodEntity food) {
        foodList.add(food);
        food.setCategory(this);
    }

    public void removeFood(FoodEntity food) {
        foodList.remove(food);
        food.setCategory(null);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<FoodEntity> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<FoodEntity> foodList) {
        this.foodList = foodList;
    }
}
