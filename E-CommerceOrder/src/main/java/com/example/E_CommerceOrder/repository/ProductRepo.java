package com.example.E_CommerceOrder.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.E_CommerceOrder.entity.Product;



	public interface ProductRepo extends JpaRepository<Product, Integer> {
		
	

}
