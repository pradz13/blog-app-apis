package com.app.blog.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int postId;

    private String title;

    @Column(length = 10000)
    private String content;

    private String imageName;

    private LocalDateTime addedDate = LocalDateTime.now();

    @ManyToOne
    private Category category;

    @ManyToOne
    private User user;
}
