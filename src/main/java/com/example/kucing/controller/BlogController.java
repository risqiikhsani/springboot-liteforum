package com.example.kucing.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.kucing.dto.BlogDTO;
import com.example.kucing.entities.Blog;
import com.example.kucing.repository.BlogRepository;
import com.example.kucing.services.BlogService;

import lombok.AllArgsConstructor;


@RestController
@RequestMapping("/api/blogs")
@AllArgsConstructor
public class BlogController {
    private final BlogService blogService;

    @GetMapping
    public ResponseEntity<Iterable<Blog>> getAllBlogs(){
        return ResponseEntity.ok(blogService.getAllBlogs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Blog> getBlogById(@PathVariable Long id){
        return ResponseEntity.ok(blogService.getBlogById(id));
    }

    @PostMapping
    public ResponseEntity<Blog> createBlog(@RequestBody BlogDTO blog){
        return ResponseEntity.status(201).body(blogService.createBlog(blog));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Blog> updateBlog(@PathVariable Long id,@RequestBody BlogDTO blog){
        return ResponseEntity.ok(blogService.updateBlog(id, blog));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBlog(@PathVariable Long id){
        blogService.deleteBlog(id);
        return ResponseEntity.noContent().build();
    }
}
