package com.example.E_CommerceOrder.service.impl;

import org.springframework.stereotype.Service;

import com.example.E_CommerceOrder.dto.ProductRequestdto;
import com.example.E_CommerceOrder.entity.Category;
import com.example.E_CommerceOrder.entity.Inventory;
import com.example.E_CommerceOrder.entity.Product;
import com.example.E_CommerceOrder.repository.CategoryRepo;
import com.example.E_CommerceOrder.repository.InventoryRepo;
import com.example.E_CommerceOrder.repository.ProductRepo;
import com.example.E_CommerceOrder.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    private  ProductRepo productRepository;
    private  CategoryRepo categoryRepository;
    private  InventoryRepo inventoryRepository;

    public ProductServiceImpl(ProductRepo productRepository,
                              CategoryRepo categoryRepository,
                              InventoryRepo inventoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.inventoryRepository = inventoryRepository;
    }
    

    @Override
    public Product addProduct(ProductRequestdto dto) {

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Product product = new Product();
        product.setProductName(dto.getProductName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setCategory(category);

        Product savedProduct = productRepository.save(product);

        Inventory inventory = new Inventory();
        inventory.setProduct(savedProduct);
        inventory.setQuantityAvailable(dto.getInitialStock());

        inventoryRepository.save(inventory);

        return savedProduct;
    }
    
}