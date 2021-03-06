package com.app.blog.service;

import java.util.List;

import com.app.blog.payloads.PostDto;
import com.app.blog.payloads.PostResponse;

public interface PostService {

    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

    public PostDto updatePost(PostDto postDto, Integer postId);

    public void deletePost(Integer postId);

    public PostResponse getAllPosts(int pageNumber, int pageSize, String sortBy);

    public PostDto getPostById(Integer postId);

    public List<PostDto> getPostsByCategory(Integer categoryId);

    public List<PostDto> getPostsByUser(Integer userId);

    public List<PostDto> searchPosts(String keyword);
}
