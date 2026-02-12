package com.example.E_CommerceOrder.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.E_CommerceOrder.entity.User;

	

	public interface UserRepo extends JpaRepository<User, Integer> {
		User findByEmail(String email);
		
	

}
