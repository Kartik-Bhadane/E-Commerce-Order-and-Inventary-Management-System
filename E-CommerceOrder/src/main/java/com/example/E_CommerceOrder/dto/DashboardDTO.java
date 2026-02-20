package com.example.E_CommerceOrder.dto;

public class DashboardDTO {

    private long totalOrders;
    private double totalRevenue;
    private long totalCustomers;
    private long totalProducts;

    public DashboardDTO(long totalOrders,
                        double totalRevenue,
                        long totalCustomers,
                        long totalProducts) {
        this.totalOrders = totalOrders;
        this.totalRevenue = totalRevenue;
        this.totalCustomers = totalCustomers;
        this.totalProducts = totalProducts;
    }

    public long getTotalOrders() {
        return totalOrders;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public long getTotalCustomers() {
        return totalCustomers;
    }

    public long getTotalProducts() {
        return totalProducts;
    }
}