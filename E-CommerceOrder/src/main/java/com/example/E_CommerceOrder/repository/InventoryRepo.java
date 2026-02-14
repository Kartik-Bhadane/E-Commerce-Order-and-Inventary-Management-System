package com.example.E_CommerceOrder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.E_CommerceOrder.entity.Inventory;

public interface InventoryRepo extends JpaRepository<Inventory, Integer> {
    
}