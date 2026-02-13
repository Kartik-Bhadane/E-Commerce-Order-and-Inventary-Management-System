package com.example.E_CommerceOrder.service;


import com.example.E_CommerceOrder.dto.ProductRequestdto;
import com.example.E_CommerceOrder.entity.Product;

public interface ProductService {

    Product addProduct(ProductRequestdto dto);
}