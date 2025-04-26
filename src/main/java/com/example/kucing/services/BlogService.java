package com.example.kucing.services;

import org.springframework.stereotype.Service;

import com.example.kucing.dto.BlogDTO;
import com.example.kucing.entities.Blog;
import com.example.kucing.entities.User;
import com.example.kucing.exception.BlogNotFoundException;
import com.example.kucing.exception.UserNotFoundException;
import com.example.kucing.repository.BlogRepository;
import com.example.kucing.repository.UserRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BlogService {
    private final BlogRepository blogRepository;
    private final UserRepository userRepository;

    public Iterable<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    public Blog getBlogById(Long id) {
        return blogRepository.findById(id).orElseThrow(() -> new BlogNotFoundException(id));
    }

    public Blog createBlog(BlogDTO blog) {
        Blog newBlog = new Blog();
        newBlog.setTitle(blog.getTitle());
        newBlog.setContent(blog.getContent());

        User user = userRepository.findById(blog.getUserId()).orElseThrow(() -> new UserNotFoundException(blog.getUserId()));
        newBlog.setUser(user);

        return blogRepository.save(newBlog); // use returned object
    }

    public Blog updateBlog(Long id, BlogDTO blog) {
        Blog existingBlog = blogRepository.findById(id).orElseThrow(() -> new BlogNotFoundException(id));
        existingBlog.setTitle(blog.getTitle());
        existingBlog.setContent(blog.getContent());
        return blogRepository.save(existingBlog); // return saved object
    }

    public void deleteBlog(Long id) {    
        if (!blogRepository.existsById(id)) {
            throw new BlogNotFoundException(id);
        }
        blogRepository.deleteById(id);
    }
}
