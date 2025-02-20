package com.java24.plantswap.controllers;

import com.java24.plantswap.models.User;
import com.java24.plantswap.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/user")

public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userRepository.save(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);

    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users);

    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ResponseEntity.ok(user);
    }
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUserById(@PathVariable String id,@Valid @RequestBody User user) {
        User existingUser = userRepository.findById(id) // find the existing user by id
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

// Update the fields of the existing user with the data from the request body
        existingUser.setName(user.getName());
        existingUser.setPhone(user.getPhone());
        existingUser.setPassword(user.getPassword());
        return ResponseEntity.ok(existingUser);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUserById(@PathVariable String id) {
        if (!userRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found");
        }
        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();

    }


}
