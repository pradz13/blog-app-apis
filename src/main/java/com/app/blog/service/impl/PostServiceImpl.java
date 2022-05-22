package com.app.blog.service.impl;

import java.time.LocalDateTime;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import com.app.blog.entity.Category;
import com.app.blog.entity.Post;
import com.app.blog.entity.User;
import com.app.blog.exceptions.ResourceNotFoundException;
import com.app.blog.payloads.PostDto;
import com.app.blog.repository.CategoryRepository;
import com.app.blog.repository.PostRepository;
import com.app.blog.repository.UserRepository;
import com.app.blog.service.PostService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));

        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));

        Post post = this.modelMapper.map(postDto, Post.class);
        post.setImageName("default.png");
        post.setUser(user);
        post.setCategory(category);
        Post createdPost = this.postRepository.save(post);
        return this.modelMapper.map(createdPost, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deletePost(Integer postId) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<PostDto> getAllPosts() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public PostDto getPostById(Integer postId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<PostDto> getPostsByCategory(Integer categoryId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<PostDto> getPostsByUser(Integer userId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
        // TODO Auto-generated method stub
        return null;
    }

}
