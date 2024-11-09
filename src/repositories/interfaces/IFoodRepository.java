package repositories.interfaces;

import exceptions.EntityAlreadyRegisteredException;
import exceptions.EntityNotFoundException;
import models.Food;
import models.enums.FoodType;

import java.util.List;

public interface IFoodRepository {

    void createFood(String name, FoodType type, int price) throws EntityAlreadyRegisteredException;
    List<Food> getFoods();
    Food getFoodById(Long id);
    Food getFoodByName(String name);
    Food updateFood(Long id, String newName, FoodType newType, int newPrice);
    Food deleteFood(Long id) throws EntityNotFoundException;

}
