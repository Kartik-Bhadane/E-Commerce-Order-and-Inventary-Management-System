package com.example.E_CommerceOrder.service;

import java.util.List;

import com.example.E_CommerceOrder.dto.ProductRequestdto;
import com.example.E_CommerceOrder.entity.Product;

public interface ProductService {

    // CREATE
    Product addProduct(ProductRequestdto dto);

    // READ
    List<Product> getAllProducts();
    Product getProductById(int id);

    // UPDATE
    Product updateProduct(int id, ProductRequestdto dto);

    // DELETE
    void deleteProduct(int id);
    
 // count the product
    public Long totalProducts();
}