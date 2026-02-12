package com.example.E_CommerceOrder.entity;



	import java.util.List;
	import jakarta.persistence.Column;
	import jakarta.persistence.Entity;
	import jakarta.persistence.GeneratedValue;
	import jakarta.persistence.GenerationType;
	import jakarta.persistence.Id;
	import jakarta.persistence.OneToMany;
	import jakarta.persistence.Table;


	@Entity
	@Table(name = "users")
	public class User {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    //1] Encapsulated  private variables
	    private int userId;

	    private String fullName;

	    @Column(unique = true, nullable = false)
	    private String email;

	    private String password;

	   
	    private String role; 

	    @OneToMany(mappedBy = "user")
	    private List<Order> orders;

		public int getUserId() {
			return userId;
		}

		public void setUserId(int userId) {
			this.userId = userId;
		}

		public String getFullName() {
			return fullName;
		}

		public void setFullName(String fullName) {
			this.fullName = fullName;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getRole() {
			return role;
		}

		public void setRole(String role) {
			this.role = role;
		}

		public List<Order> getOrders() {
			return orders;
		}

		public void setOrders(List<Order> orders) {
			this.orders = orders;
		}

	    
	
}
