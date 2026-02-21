package com.example.E_CommerceOrder.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    private final ProductRepo productRepository;
    private final CategoryRepo categoryRepository;
    private final InventoryRepo inventoryRepository;

    public ProductServiceImpl(ProductRepo productRepository,
                              CategoryRepo categoryRepository,
                              InventoryRepo inventoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    @Transactional
    public Product addProduct(ProductRequestdto dto) {

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Product product = new Product();
        product.setProductName(dto.getProductName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setCategory(category);
        product.setImageUrl(dto.getImageUrl());

        Inventory inventory = new Inventory();
        inventory.setQuantityAvailable(dto.getInitialStock());

        Inventory savedInventory = inventoryRepository.save(inventory);
        product.setInventory(savedInventory);

        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(int id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Override
    @Transactional
    public Product updateProduct(int id, ProductRequestdto dto) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        product.setProductName(dto.getProductName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setCategory(category);

        if (dto.getInitialStock() != null) {
            product.getInventory().setQuantityAvailable(dto.getInitialStock());
        }

        return productRepository.save(product);
    }

    @Override
    @Transactional
    public void deleteProduct(int id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        inventoryRepository.delete(product.getInventory());
        productRepository.delete(product);
    }
    
    @Override
    public Long totalProducts() {
    	
    	return productRepository.count();
    }
}
