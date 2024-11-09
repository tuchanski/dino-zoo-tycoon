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

    public void createFood(String name, FoodType type, int price) {
        try {
            foodRepository.createFood(name, type, price);
        } catch (EntityAlreadyRegisteredException e) {
            System.out.println("Error: " + e.getMessage());
        }
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

    public void updateFood(int id, String newName, FoodType newType, int newPrice) {
        try {
            foodRepository.updateFoodById((long) id, newName, newType, newPrice);
        } catch (EntityAlreadyRegisteredException | EntityNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public Food deleteFood(int id) {

        Food food = null;

        try {
            food = foodRepository.deleteFoodById((long) id);
        } catch (EntityNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return food;
    }

}
