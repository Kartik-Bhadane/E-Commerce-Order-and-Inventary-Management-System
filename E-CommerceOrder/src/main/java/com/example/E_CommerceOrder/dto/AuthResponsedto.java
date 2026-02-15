package com.example.E_CommerceOrder.dto;

public class AuthResponsedto {

    private String token;
    private String role;
    private int userId;

    // ✅ REQUIRED by Jackson
    public AuthResponsedto() {
    }

    // ✅ MAIN constructor (use this everywhere)
    public AuthResponsedto(String token, String role, int userId) {
        this.token = token;
        this.role = role;
        this.userId = userId;
    }

    // ✅ Getters & Setters

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}