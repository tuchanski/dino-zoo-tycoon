package repositories.interfaces;

import models.Food;
import models.FoodStock;
import models.enums.FoodType;

import java.util.List;

public interface IFoodStockRepository {

    void addFood(FoodType foodType, int amount);
    void removeFood(FoodType foodType, int amount);
    List<FoodStock> getFoodStock();
    FoodStock getFoodStockByType(FoodType foodType);

}
