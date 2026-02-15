package com.example.E_CommerceOrder.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.E_CommerceOrder.dto.ProductRequestdto;
import com.example.E_CommerceOrder.entity.Product;
import com.example.E_CommerceOrder.service.ProductService;

@RestController
@RequestMapping("/api/admin/products")   
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public Product addProduct(@RequestBody ProductRequestdto dto) {
        return productService.addProduct(dto);
    }

 
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

  
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id) {
        return productService.getProductById(id);
    }

    
    @PutMapping("/{id}")
    public Product updateProduct(
            @PathVariable int id,
            @RequestBody ProductRequestdto dto) {
        return productService.updateProduct(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
    }
}