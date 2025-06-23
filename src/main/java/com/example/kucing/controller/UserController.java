package com.example.kucing.controller;

import com.example.kucing.entities.User;
import com.example.kucing.repository.UserRepository;
import com.example.kucing.specification.UserSpecifications;

import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {
    private final UserRepository userRepository;

    // @GetMapping
    // public List<User> getAllUsers(){
    // return userRepository.findAll();
    // }

    @GetMapping
    public Page<User> getAllUsers(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String email,
            Pageable pageable) {
        Specification<User> spec = Specification.where(UserSpecifications.containsKeyword(search))
                .and(UserSpecifications.hasUsername(username))
                .and(UserSpecifications.hasEmail(email));

        return userRepository.findAll(spec, pageable);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        User existingUser = userRepository.findById(id).orElseThrow();
        existingUser.setFullname(user.getFullname());
        existingUser.setEmail(user.getEmail());
        existingUser.setUsername(user.getUsername());
        existingUser.setPassword(user.getPassword());
        return userRepository.save(existingUser);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
    }
}
