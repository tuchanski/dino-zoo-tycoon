package controllers;

import exceptions.EntityAlreadyRegisteredException;
import exceptions.EntityNotFoundException;
import models.Food;
import models.enums.FoodType;
import repositories.FoodRepository;
import repositories.interfaces.IFoodRepository;

import java.util.List;

public class FoodController {

    private IFoodRepository foodRepository = new FoodRepository();

    public void createFood(String name, FoodType type, int price) throws EntityAlreadyRegisteredException {
        foodRepository.createFood(name, type, price);
    }

    public Food getFoodByName(String name) {
        return foodRepository.getFoodByName(name);
    }

    public Food getFoodById(int id) {
        return foodRepository.getFoodById((long) id);
    }
    
    public List<Food> getFoods() {
        return foodRepository.getFoods();
    }

    public void updateFood(int id, String newName, FoodType newType, int newPrice) throws EntityAlreadyRegisteredException, EntityNotFoundException {
        foodRepository.updateFoodById((long) id, newName, newType, newPrice);
    }

    public Food deleteFood(int id) throws EntityNotFoundException {
        return foodRepository.deleteFoodById((long) id);
    }

}
