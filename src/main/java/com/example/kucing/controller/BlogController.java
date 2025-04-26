package com.example.kucing.controller;

import com.example.kucing.entities.Blog;
import com.example.kucing.entities.User;
import com.example.kucing.repository.BlogRepository;
import com.example.kucing.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/blogs")
@AllArgsConstructor
public class BlogController {
    private final BlogRepository blogRepository;
    private final UserRepository userRepository;

    @GetMapping
    public List<Blog> getAllBlogs(){
        return blogRepository.findAll();
    }

    @GetMapping("/{id}")
    public Blog getBlogById(@PathVariable Long id){
        return blogRepository.findById(id).orElseThrow();
    }

    @PostMapping
    public Blog createBlog(@RequestBody Blog blog){
        return blogRepository.save(blog);
    }

    @PutMapping("/{id}")
    public Blog updateBlog(@PathVariable Long id,@RequestBody Blog blog){
        Blog existingBlog = blogRepository.findById(id).orElseThrow();
        existingBlog.setTitle(blog.getTitle());
        existingBlog.setContent(blog.getContent());
        return blogRepository.save(blog);
    }

    @DeleteMapping("/{id}")
    public void deleteBlog(@PathVariable Long id){
        blogRepository.deleteById(id);
    }
}
