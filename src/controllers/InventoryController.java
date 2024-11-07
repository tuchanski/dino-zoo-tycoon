package controllers;

import models.Inventory;
import repositories.InventoryRepositoryImpl;
import repositories.interfaces.IInventoryRepository;

import java.util.List;

public class InventoryController {

    private final IInventoryRepository inventoryRepository = new InventoryRepositoryImpl();

    public void createInventory(Long zooId) {
        inventoryRepository.createInventory(zooId);
    }

    public List<Inventory> getAllInventories(){
        return inventoryRepository.getAllInventories();
    }

    public Inventory getInventoryById(Long id){
        return inventoryRepository.getInventoryById(id);
    }

    public Inventory deleteInventoryById(Long id){
        return inventoryRepository.deleteInventoryById(id);
    }

}
