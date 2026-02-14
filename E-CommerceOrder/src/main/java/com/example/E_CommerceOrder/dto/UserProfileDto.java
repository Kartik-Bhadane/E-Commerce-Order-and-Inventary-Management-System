package com.example.E_CommerceOrder.dto;

public class UserProfileDto {

    private int userId;
    private String fullName;
    private String email;
    private String role;

    public UserProfileDto(int userId,
                               String fullName,
                               String email,
                               String role) {
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }
}