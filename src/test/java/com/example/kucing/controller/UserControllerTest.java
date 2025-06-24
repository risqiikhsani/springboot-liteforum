package com.example.kucing.controller;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.kucing.entities.User;
import com.example.kucing.repository.UserRepository;

@WebMvcTest(UserController.class)
public class UserControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean    
    // it uses real mock
    // Use this if you want to test controller logic without relying on the actual service or DB.
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    void setup() {
        user = new User();
        user.setId(1L);
        user.setFullname("John Doe");
        user.setEmail("john@example.com");
        user.setUsername("johndoe");
        user.setPassword("password");
    }

    @Test
    void getAllUsers() throws Exception{
        
        given(userRepository.findAll()).willReturn(List.of(user));
        // This means:
        // When userRepository.findAll() is called in the test, return a list containing user.

        mockMvc.perform(get("/api/users"))
            .andExpect(status().isOk());
            // .andExpect(jsonPath("$[0].fullname").value("John Doe"))
            // .andExpect(jsonPath("$[0].email").value("john@example.com"))
            // .andExpect(jsonPath("$[0].username").value("johndoe"))
            // .andExpect(jsonPath("$[0].password").value("password"));
    }

    @Test
    void getUserById() throws Exception{
        given(userRepository.findById(1L)).willReturn(Optional.of(user));

        mockMvc.perform(get("/api/users/1"))            
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.fullname").value("John Doe"))            
            .andExpect(jsonPath("$.email").value("john@example.com"))
            .andExpect(jsonPath("$.username").value("johndoe"))
            .andExpect(jsonPath("$.password").value("password"));   
    }

    @Test
    void createUser() throws Exception{
        given(userRepository.save(ArgumentMatchers.any(User.class))).willReturn(user);

        mockMvc.perform(post("/api/users")
            .contentType("application/json")
            .content("{\"fullname\": \"John Doe\", \"email\": \"john@example.com\", \"username\": \"johndoe\", \"password\": \"password\"}"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.email").value("john@example.com"));
    }   

    @Test
    void updateUser() throws Exception{
        given(userRepository.findById(1L)).willReturn(Optional.of(user));
        given(userRepository.save(ArgumentMatchers.any(User.class))).willReturn(user);

        mockMvc.perform(put("/api/users/1")
            .contentType("application/json")
            .content("{\"fullname\": \"Updated name\", \"email\": \"updated@example.com\", \"username\": \"updated\", \"password\": \"password\"}"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.email").value("updated@example.com"));
    }

    @Test
    void deleteUser() throws Exception{
        doNothing().when(userRepository).deleteById(1L);

        mockMvc.perform(delete("/api/users/1"))
            .andExpect(status().isOk());
    }
}
