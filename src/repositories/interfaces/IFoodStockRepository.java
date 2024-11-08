package repositories.interfaces;

import models.Food;
import models.FoodStock;
import models.enums.FoodType;

import java.util.List;

public interface IFoodStockRepository {

    void addFood(Long foodId, int amount);
    void removeFood(Long foodId, int amount);
    List<FoodStock> getFoodStock();

}
