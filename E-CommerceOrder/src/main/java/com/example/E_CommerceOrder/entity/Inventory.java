package com.example.E_CommerceOrder.entity;


	import jakarta.persistence.Entity;
	import jakarta.persistence.GeneratedValue;
	import jakarta.persistence.GenerationType;
	import jakarta.persistence.Id;
	import jakarta.persistence.JoinColumn;
	import jakarta.persistence.OneToOne;

	@Entity
	public class Inventory {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int inventoryId;

	    private Integer quantityAvailable;

	    @OneToOne
	    @JoinColumn(name = "product_id")
	    private Product product;

		public int getInventoryId() {
			return inventoryId;
		}

		public void setInventoryId(int inventoryId) {
			this.inventoryId = inventoryId;
		}

		public Integer getQuantityAvailable() {
			return quantityAvailable;
		}

		public void setQuantityAvailable(Integer quantityAvailable) {
			this.quantityAvailable = quantityAvailable;
		}

		public Product getProduct() {
			return product;
		}

		public void setProduct(Product product) {
			this.product = product;
		}

	    
	
}
