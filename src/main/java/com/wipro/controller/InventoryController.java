package com.wipro.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.model.Inventory;
import com.wipro.service.InventoryService;

@RequestMapping("/api/inventory")
@RestController
public class InventoryController {
    
    @Autowired
    private InventoryService service;

    @GetMapping("/all")
    public ResponseEntity<List<Inventory>> getAllInventory() {
        List<Inventory> inventories = service.listAllInventories();
        return new ResponseEntity<List<Inventory>>(inventories, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Inventory> findInventoryByProductId(@PathVariable("id") int id) {
        Inventory inventory = service.getInventoryByProductId(id);
        return new ResponseEntity<Inventory>(inventory, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Inventory> saveInventory(@RequestBody Inventory inventory) {
        Inventory savedInventory = service.addInventory(inventory);
        return new ResponseEntity<Inventory>(savedInventory, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Inventory> updateInventory(@PathVariable("id") int id, @RequestBody Inventory inventory) {
        Inventory updatedInventory = service.updateInventorybyProductId(inventory, id);
        return new ResponseEntity<Inventory>(updatedInventory, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteInventory(@PathVariable("id") int id) {
        Map<String, Boolean> response = service.deleteInventoryById(id);
        return new ResponseEntity<Map<String, Boolean>>(response, HttpStatus.OK);
    }
}
