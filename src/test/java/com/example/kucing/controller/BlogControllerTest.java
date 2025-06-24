package com.example.kucing.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.kucing.entities.Blog;
import com.example.kucing.entities.User;
import com.example.kucing.repository.BlogRepository;
import com.example.kucing.repository.UserRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class BlogControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BlogRepository blogRepository;

    @BeforeEach
    void setup() {
        userRepository.deleteAll();
        blogRepository.deleteAll();

        User user = new User();
        user.setEmail("test@example.com");
        user.setFullname("test user");
        user.setUsername("testuser");
        user.setPassword("password");
        userRepository.save(user);

        Blog blog = new Blog();
        blog.setTitle("new title");
        blog.setContent("new content");
        blog.setUser(user);
        blogRepository.save(blog);
    }

    @Test
    void testCreateBlog() throws Exception {
        Long userId = userRepository.findAll().iterator().next().getId();

        mockMvc.perform(post("/api/blogs")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"New Blog\",\"content\":\"New Content\",\"userId\":"+userId+"}"))
            .andExpect(status().isCreated());
    }

    @Test
    void testGetBlogs() throws Exception {
        mockMvc.perform(get("/api/blogs"))
            .andDo(print())
            .andExpect(status().isOk());
            // .andExpect(jsonPath("$[0].title").value("Blog 1"))
            // .andExpect(jsonPath("$[0].content").value("Content 1"))
            // .andExpect(jsonPath("$[0].userId").value(1));
    }
}