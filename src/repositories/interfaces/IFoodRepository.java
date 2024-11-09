package repositories.interfaces;

import exceptions.EntityAlreadyRegisteredException;
import models.Food;
import models.enums.FoodType;

import java.util.List;

public interface IFoodRepository {

    void createFood(String name, FoodType type, int price) throws EntityAlreadyRegisteredException;
    List<Food> getFoods();
    Food getFoodById(Long id);
    Food getFoodByName(String name);
    void updateFood(Long id, String newName, FoodType newType, int newPrice);
    void deleteFood(Long id);

}
