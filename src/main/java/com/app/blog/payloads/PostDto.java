package com.app.blog.payloads;

import com.app.blog.entity.Category;
import com.app.blog.entity.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostDto {
    private String title;
    private String content;
    private String imageName;
    private Category category;
    private User user;
}
