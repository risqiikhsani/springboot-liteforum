package com.example.kucing.dto;

import com.example.kucing.entities.Blog;
import com.example.kucing.entities.User;

import lombok.Data;

@Data
public class BlogResponseDTO {
    private Long id;
    private String title;
    private String content;
    private UserResponseDTO user;
}
