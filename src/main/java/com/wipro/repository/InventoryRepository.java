package com.wipro.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.model.Inventory;


@Repository
public interface InventoryRepository extends JpaRepository<Inventory,Integer> {
    
    public Optional<Inventory> findByProductId(int productId);
    public Boolean existsByProductId(int productId);
}
