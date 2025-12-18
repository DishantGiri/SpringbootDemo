package com.example.nobs.product;

import com.example.nobs.product.model.CustomUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateNewUserController {
    private static final Logger log = LoggerFactory.getLogger(CreateNewUserController.class);
    private final PasswordEncoder passwordEncoder;
    private final CustomUserRepo customUserRepo;

    public CreateNewUserController(PasswordEncoder passwordEncoder, CustomUserRepo customUserRepo) {
        this.passwordEncoder = passwordEncoder;
        this.customUserRepo = customUserRepo;
    }

    @PostMapping("/createNewUser")
    public ResponseEntity<String> createNewUser(@RequestBody CustomUser user) {
        // validate request body
        if (user == null) {
            return ResponseEntity.badRequest().body("Request body is missing");
        }
        String username = user.getUsername();
        String password = user.getPassword();
        if (username == null || username.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Username must be provided");
        }
        if (password == null || password.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Password must be provided");
        }

        try {
            log.info("Attempting to create user: {}", username);
            if (customUserRepo.existsById(username)) {
                log.warn("User already exists: {}", username);
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Failed to create user: Username already exists");
            }

            CustomUser newUser = new CustomUser();
            newUser.setUsername(username);
            newUser.setPassword(passwordEncoder.encode(password));
            customUserRepo.save(newUser);
            log.info("Successfully created user: {}", username);
            return ResponseEntity.ok("User created successfully");
        } catch (DataAccessException dae) {
            log.error("Database error while creating user: {}", username, dae);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Database error: " + dae.getMessage());
        } catch (Exception ex) {
            log.error("Unexpected error while creating user: {}", username, ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error: " + ex.getMessage());
        }
    }
}
