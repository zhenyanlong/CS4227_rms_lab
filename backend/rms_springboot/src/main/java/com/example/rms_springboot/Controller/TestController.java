package com.example.rms_springboot.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/test-db")
    public String testDb() {
        try {
            jdbcTemplate.queryForObject("SELECT 1", Integer.class);
            return "Database connection OK!";
        } catch (Exception e) {
            return "Database connection FAILED: " + e.getMessage();
        }
    }
}