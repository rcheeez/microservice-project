package com.wipro.service;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.wipro.exception.InventoryNotFoundException;
import com.wipro.model.Inventory;

@FeignClient(name = "inventory-service", url = "${pivotal.inventoryservice.url}")
public interface InventoryProxy {
    
    	@PutMapping("/api/inventory/update/{productId}")
	    public Inventory updateInventory(@PathVariable int productId, @RequestBody Inventory inventory) throws InventoryNotFoundException;

	    @GetMapping("/api/inventory/get/{productId}")
	    public Optional<Inventory> getByProductId(@PathVariable int productId) throws InventoryNotFoundException;

}
