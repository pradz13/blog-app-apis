package com.app.blog.controller;

import com.app.blog.payloads.ApiResponse;
import com.app.blog.payloads.PostDto;
import com.app.blog.payloads.PostResponse;
import com.app.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,
                                              @PathVariable Integer userId,
                                              @PathVariable Integer categoryId) {

        PostDto createdPostDto = this.postService.createPost(postDto, userId, categoryId);
        return new ResponseEntity<>(createdPostDto, HttpStatus.CREATED);
    }

    @PutMapping("/post/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,
                                              @PathVariable Integer postId) {

        PostDto createdPostDto = this.postService.updatePost(postDto, postId);
        return new ResponseEntity<>(createdPostDto, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId) {
        List<PostDto> postsByUser = this.postService.getPostsByUser(userId);
        return ResponseEntity.ok(postsByUser);
    }

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer categoryId) {
        List<PostDto> postsByCategory = this.postService.getPostsByCategory(categoryId);
        return ResponseEntity.ok(postsByCategory);
    }

    @GetMapping("/posts")
    public ResponseEntity<PostResponse>
        getAllPosts(@RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
                    @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize,
                    @RequestParam(value = "sortBy", defaultValue = "postId", required = false) String sortBy) {

        PostResponse postResponse = this.postService.getAllPosts(pageNumber, pageSize, sortBy);
        return ResponseEntity.ok(postResponse);
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId) {
        PostDto postDto = this.postService.getPostById(postId);
        return ResponseEntity.ok(postDto);
    }

    @DeleteMapping("/post/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId) {
        this.postService.deletePost(postId);
        return new ResponseEntity<>(new ApiResponse("Post deleted successfully", true), HttpStatus.OK);
    }

    @GetMapping("/search/post/{keyword}")
    public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable String keyword) {
        List<PostDto> posts = this.postService.searchPosts(keyword);
        return ResponseEntity.ok(posts);
    }
}
