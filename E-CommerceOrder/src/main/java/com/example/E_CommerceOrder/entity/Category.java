package com.example.E_CommerceOrder.entity;






	import java.util.List;

	import jakarta.persistence.Entity;
	import jakarta.persistence.GeneratedValue;
	import jakarta.persistence.GenerationType;
	import jakarta.persistence.Id;
	import jakarta.persistence.OneToMany;

	@Entity
	public class Category {

	
	

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int  categoryId;

	    private String categoryName;

	    private String description;

	    @OneToMany(mappedBy = "category")
	    private List<Product> products;

		public int getCategoryId() {
			return categoryId;
		}

		public void setCategoryId(int categoryId) {
			this.categoryId = categoryId;
		}

		public String getCategoryName() {
			return categoryName;
		}

		public void setCategoryName(String categoryName) {
			this.categoryName = categoryName;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public List<Product> getProducts() {
			return products;
		}

		public void setProducts(List<Product> products) {
			this.products = products;
		}

	    
	    // getters & setters
	    
	}

