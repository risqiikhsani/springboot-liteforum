package com.example.kucing.dto;

import lombok.Data;

@Data
public class BlogResponseDTO {
    private Long id;
    private String title;
    private String content;
    private UserResponseDTO user;
}
