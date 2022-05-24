package com.app.blog.service.impl;

import java.time.LocalDateTime;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import com.app.blog.entity.Category;
import com.app.blog.entity.Post;
import com.app.blog.entity.User;
import com.app.blog.exceptions.ResourceNotFoundException;
import com.app.blog.payloads.PostDto;
import com.app.blog.payloads.PostResponse;
import com.app.blog.repository.CategoryRepository;
import com.app.blog.repository.PostRepository;
import com.app.blog.repository.UserRepository;
import com.app.blog.service.PostService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
        post.setAddedDate(LocalDateTime.now());
        Post createdPost = this.postRepository.save(post);
        return this.modelMapper.map(createdPost, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post = this.postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));

        post.setImageName("default.png");
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        Post updatedPost = this.postRepository.save(post);
        return this.modelMapper.map(updatedPost, PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {
        Post post = this.postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));

        this.postRepository.delete(post);
    }

    @Override
    public PostResponse getAllPosts(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Post> pagePost = this.postRepository.findAll(pageable);
        List<Post> allPosts = pagePost.getContent();
        List<PostDto> allPostDtos = allPosts
                .stream()
                .map(post -> this.modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(allPostDtos);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElements(pagePost.getTotalElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());
        return postResponse;
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post post = this.postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));

        return this.modelMapper.map(post, PostDto.class);
    }

    @Override
    public List<PostDto> getPostsByCategory(Integer categoryId) {
        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));

        List<Post> postsByCategory = this.postRepository.findByCategory(category);
        return postsByCategory
                .stream()
                .map(post -> this.modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());

    }

    @Override
    public List<PostDto> getPostsByUser(Integer userId) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));

        List<Post> postsByUser = this.postRepository.findByUser(user);
        return postsByUser
                .stream()
                .map(post -> this.modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());

    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
        // TODO Auto-generated method stub
        return null;
    }

}
