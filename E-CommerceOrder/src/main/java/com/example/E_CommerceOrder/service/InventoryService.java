package com.example.E_CommerceOrder.service;

import java.util.List;

import com.example.E_CommerceOrder.dto.InventoryRequestdto;
import com.example.E_CommerceOrder.entity.Inventory;

public interface InventoryService {

    Inventory addStock(InventoryRequestdto dto);

    Inventory updateStock(int inventoryId, InventoryRequestdto dto);

    Inventory getInventoryById(int inventoryId);

    List<Inventory> getAllInventory();

    void deleteInventory(int inventoryId);
}