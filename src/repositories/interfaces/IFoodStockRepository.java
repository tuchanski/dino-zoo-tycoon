package repositories.interfaces;

import exceptions.EntityNotFoundException;
import models.Food;
import models.FoodStock;
import models.enums.FoodType;

import java.util.List;

public interface IFoodStockRepository {

    void createStock();
    void addFood(Long foodId, int amount) throws EntityNotFoundException;
    void removeFood(Long foodId, int amount) throws EntityNotFoundException;
    List<FoodStock> getFoodStock();

}
