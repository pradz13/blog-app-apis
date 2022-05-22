package com.app.blog.repository;

import java.util.List;

import com.app.blog.entity.Category;
import com.app.blog.entity.Post;
import com.app.blog.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {

    List<Post> findByUser(User user);

    List<Post> findByCategory(Category category);

}
