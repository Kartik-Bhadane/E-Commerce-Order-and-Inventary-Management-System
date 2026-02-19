package com.example.E_CommerceOrder.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.E_CommerceOrder.entity.Product;
import com.example.E_CommerceOrder.service.ProductService;

@RestController
@RequestMapping("/api/products")   // 🔥 PUBLIC PATH
public class PublicProductController {

    private final ProductService productService;

    public PublicProductController(ProductService productService) {
        this.productService = productService;
    }

    // 🔓 PUBLIC: CUSTOMER CAN SEE PRODUCTS
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // 🔓 OPTIONAL: VIEW SINGLE PRODUCT
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id) {
        return productService.getProductById(id);
    }
}