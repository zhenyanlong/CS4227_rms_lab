package com.example.rms_springboot.dto;

public class AuthResponse {
    private String token;

    // 构造函数
    public AuthResponse(String token) {
        this.token = token;
    }

    // Getter
    public String getToken() {
        return token;
    }

    // Setter
    public void setToken(String token) {
        this.token = token;
    }
}