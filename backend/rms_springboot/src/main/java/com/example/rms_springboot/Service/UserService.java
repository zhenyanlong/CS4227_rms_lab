package com.example.rms_springboot.Service;


import com.example.rms_springboot.DataModel.User;
import com.example.rms_springboot.Repository.UserRepository;
import com.example.rms_springboot.Security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    @Autowired
    public UserService(UserRepository userRepository,
                       @Lazy PasswordEncoder passwordEncoder,
                       JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
    }

    // User Register
    @Transactional
    public void registerUser(String username, String password) {
        if (userRepository.existsByUsername(username)) {
            throw new RuntimeException("Username already exists");
        }

        User user = new User(username,passwordEncoder.encode(password));

        userRepository.save(user);
    }

    // User login authentication
    public String authenticateUser(String username, String password) {
        System.out.println("==== Start User authentication ====");
        System.out.println("Enter username: " + username);

        // 1. Query user
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> {
                    System.out.println("⚠️ User does not exist: " + username);
                    return new UsernameNotFoundException("User does not exist");
                });

        System.out.println("Database user information: " + user.toString());

        // 2. Authentication password
        if (!passwordEncoder.matches(password, user.getPassword())) {
            System.out.println("❌ Password mismatch");
            System.out.println("Input Password: " + password);
            System.out.println("Store Password: " + user.getPassword());
            throw new BadCredentialsException("Password error");
        }

        // 3. Generate Token
        String token = jwtUtils.generateToken(username);
        System.out.println("✅ Generate Token: " + token);

        return token;
    }

    // Spring Security required methods
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
    }

    // Get user details
    public User getUserDetails(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}