package com.example.E_CommerceOrder.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.E_CommerceOrder.dto.InventoryRequestdto;
import com.example.E_CommerceOrder.entity.Inventory;
import com.example.E_CommerceOrder.entity.Product;
import com.example.E_CommerceOrder.repository.InventoryRepo;
import com.example.E_CommerceOrder.repository.ProductRepo;
import com.example.E_CommerceOrder.service.InventoryService;

@Service
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepo inventoryRepo;
    private final ProductRepo productRepo;

    public InventoryServiceImpl(InventoryRepo inventoryRepo, ProductRepo productRepo) {
        this.inventoryRepo = inventoryRepo;
        this.productRepo = productRepo;
    }

    @Override
    public Inventory addStock(InventoryRequestdto dto) {

        Product product = productRepo.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (product.getInventory() != null) {
            throw new RuntimeException("Inventory already exists for this product");
        }

        Inventory inventory = new Inventory();
        inventory.setQuantityAvailable(dto.getQuantity());

        Inventory savedInventory = inventoryRepo.save(inventory);

        product.setInventory(savedInventory);
        productRepo.save(product);

        return savedInventory;
    }

    @Override
    public Inventory updateStock(int inventoryId, InventoryRequestdto dto) {

        Inventory inventory = inventoryRepo.findById(inventoryId)
                .orElseThrow(() -> new RuntimeException("Inventory not found"));

        inventory.setQuantityAvailable(dto.getQuantity());
        return inventoryRepo.save(inventory);
    }

    @Override
    public Inventory getInventoryById(int inventoryId) {
        return inventoryRepo.findById(inventoryId)
                .orElseThrow(() -> new RuntimeException("Inventory not found"));
    }

    @Override
    public List<Inventory> getAllInventory() {
        return inventoryRepo.findAll();
    }

    @Override
    public void deleteInventory(int inventoryId) {
        inventoryRepo.deleteById(inventoryId);
    }
}