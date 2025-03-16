package com.example.rms_springboot.Repository;

import com.example.rms_springboot.DataModel.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    // Check whether the user name exists
    boolean existsByUsername(String username);

    // Find a user by user name
    Optional<User> findByUsername(String username);
}