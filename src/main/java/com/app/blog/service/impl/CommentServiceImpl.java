package com.app.blog.service.impl;

import com.app.blog.entity.Comment;
import com.app.blog.entity.Post;
import com.app.blog.exceptions.ResourceNotFoundException;
import com.app.blog.payloads.CommentDto;
import com.app.blog.repository.CommentRepository;
import com.app.blog.repository.PostRepository;
import com.app.blog.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {
        Comment comment = this.modelMapper.map(commentDto, Comment.class);
        Post post = this.postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));

        comment.setPost(post);
        Comment createdComment = this.commentRepository.save(comment);
        return this.modelMapper.map(createdComment, CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
        this.commentRepository.deleteById(commentId);
    }
}
