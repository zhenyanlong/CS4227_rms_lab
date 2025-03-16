package com.example.rms_springboot.DataModel;


import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Column(unique = true)
    private String username;

    private String password; // BCrypt encrypted storage

    public User(String username, String password){
        this.username=username;
        this.password=password;
    }

    public User() {

    }
}
