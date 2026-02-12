package com.example.E_CommerceOrder.dto;
public class ProductRequestdto {

    private String productName;
    private String description;
    private Double price;
    private int categoryId;
    private Integer initialStock;   

    public ProductRequestdto() {
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

  
    public Integer getInitialStock() {
        return initialStock;
    }

    public void setInitialStock(Integer initialStock) {
        this.initialStock = initialStock;
    }
}