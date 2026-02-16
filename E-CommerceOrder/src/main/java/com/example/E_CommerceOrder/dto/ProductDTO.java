package com.example.E_CommerceOrder.dto;

public class ProductDTO {

    private int productId;
    private String productName;
    private Double price;
    private String description;
    private int categoryId;
    private int stockQuantity;

    public ProductDTO() {}

    public ProductDTO(int productId,
                              String productName,
                              Double price,
                              String description,
                              int categoryId,
                              int stockQuantity) {

        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.description = description;
        this.categoryId = categoryId;
        this.stockQuantity = stockQuantity;
    }

    // Getters & Setters
    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getCategoryId() { return categoryId; }
    public void setCategoryId(int categoryId) { this.categoryId = categoryId; }

    public int getStockQuantity() { return stockQuantity; }
    public void setStockQuantity(int stockQuantity) { this.stockQuantity = stockQuantity; }
}
