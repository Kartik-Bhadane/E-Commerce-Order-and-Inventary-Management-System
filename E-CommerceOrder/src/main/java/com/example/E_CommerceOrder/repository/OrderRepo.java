package com.example.E_CommerceOrder.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.E_CommerceOrder.entity.Order;



	public interface OrderRepo extends JpaRepository<Order, Integer> {

	

}
