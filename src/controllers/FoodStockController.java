package controllers;

import exceptions.EntityNotFoundException;
import models.FoodStock;
import models.Zoo;
import repositories.FoodStockRepositoryImpl;
import repositories.interfaces.IFoodStockRepository;

import java.util.List;

public class FoodStockController {

    private final IFoodStockRepository stockRepository;

    public FoodStockController(Zoo zoo) {
       stockRepository = new FoodStockRepositoryImpl(zoo);
    }

    public void createStock(){
        stockRepository.createStock();
    }

    public void addFood(int foodId, int amount) {

        try {
            stockRepository.addFood((long) foodId, amount);
        } catch (EntityNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void removeFood(int foodId, int amount) {
        try {
            stockRepository.removeFood((long) foodId, amount);
        } catch (EntityNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public List<FoodStock> getFoodStock() {
        return stockRepository.getFoodStock();
    }

}
