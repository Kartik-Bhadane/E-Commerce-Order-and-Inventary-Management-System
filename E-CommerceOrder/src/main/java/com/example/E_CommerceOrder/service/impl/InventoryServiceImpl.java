package com.example.E_CommerceOrder.service.impl;

import org.springframework.stereotype.Service;

import com.example.E_CommerceOrder.entity.Inventory;
import com.example.E_CommerceOrder.entity.Product;
import com.example.E_CommerceOrder.repository.InventoryRepo;
import com.example.E_CommerceOrder.repository.ProductRepo;
import com.example.E_CommerceOrder.service.InventoryService;


@Service
public class InventoryServiceImpl implements InventoryService {

    private  InventoryRepo inventoryRepository;
    private  ProductRepo productRepository;

    public InventoryServiceImpl(InventoryRepo inventoryRepository,
                                ProductRepo productRepository) {
        this.inventoryRepository = inventoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void updateStock(int productId, int quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Inventory inventory = inventoryRepository.findByProduct(product);
        inventory.setQuantityAvailable(quantity);

        inventoryRepository.save(inventory);
    }
}