package com.app.blog.service;

import com.app.blog.payloads.CommentDto;

public interface CommentService {

    public CommentDto createComment(CommentDto commentDto, Integer postId);

    void deleteComment(Integer commentId);
}
