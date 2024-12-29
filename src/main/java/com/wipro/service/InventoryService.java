package com.wipro.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.exception.BadRequestException;
import com.wipro.exception.NotFoundException;
import com.wipro.model.Inventory;
import com.wipro.repository.InventoryRepository;

@Service
public class InventoryService {
    
    @Autowired
    private InventoryRepository repository;

    public List<Inventory> listAllInventories() {
        List<Inventory> inventories = repository.findAll();
        if (inventories.isEmpty()) {
            throw new NotFoundException("There are no inventories in the database!");
        }
        return inventories;
    }

    public Inventory getInventoryByProductId(int id) {
        Optional<Inventory> invOpt = repository.findByProductId(id);
        if (invOpt.isEmpty()) {
            throw new NotFoundException("Inventory Not Found with this Product Id"+ id);
        } 
        Inventory inventory = invOpt.get();
        return inventory;
    }

    public Inventory addInventory(Inventory inventory) {
        if (repository.existsByProductId(inventory.getProductId())) {
            throw new BadRequestException("Inventory Data of this Product Id already exists");
        }

        Inventory newInventory = repository.save(inventory);
        return newInventory;
    }

    public Inventory updateInventorybyProductId(Inventory inventory, int productId) {
        Optional<Inventory> invOpt = repository.findByProductId(productId);
        if (invOpt.isEmpty()) {
            throw new NotFoundException("Inventory Not Found with this ProductId: "+ productId);
        } 
        Inventory inv = invOpt.get();
        inv.setQuantity(inventory.getQuantity());
        Inventory updatedInventory = repository.save(inv);
        return updatedInventory;
    }

    public Map<String, Boolean> deleteInventoryById(int id) {
        Optional<Inventory> invOpt = repository.findById(id);
        Map<String, Boolean> response = new HashMap<String, Boolean>();
        if (invOpt.isEmpty()) {
            throw new NotFoundException("Inventory Not Found with this Id"+ id);
        } 
        Inventory inventory = invOpt.get();
        repository.delete(inventory);
        response.put("Inventory Deleted Successfully!", true);
        return response;
    }
}
