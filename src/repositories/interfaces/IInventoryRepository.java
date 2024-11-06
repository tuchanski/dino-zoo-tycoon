package repositories.interfaces;

import models.Inventory;
import java.util.List;

public interface IInventoryRepository {
    void createInventory(Long zooId);
    Inventory getInventoryById(Long id);
    Inventory deleteInventoryById(Long id);
    List<Inventory> getAllInventories();
}
