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

    public void addFood(int foodId, int amount) throws EntityNotFoundException {
        stockRepository.addFood((long) foodId, amount);
    }

    public void removeFood(int foodId, int amount) throws EntityNotFoundException {
        stockRepository.removeFood((long) foodId, amount);
    }

    public List<FoodStock> getFoodStock() throws EntityNotFoundException {
        return stockRepository.getFoodStock();
    }

}
