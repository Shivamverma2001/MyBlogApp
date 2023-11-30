package com.sv.myblogapp.service;

import com.sv.myblogapp.entity.Comment;
import com.sv.myblogapp.entity.Post;
import com.sv.myblogapp.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    CommentRepository commentRepository;
    public CommentServiceImpl(CommentRepository commentRepository){
        this.commentRepository=commentRepository;
    }

    @Override
    public void deleteById(int id) {
        Comment comment = commentRepository.findById(id).orElse(null);
        if (comment != null) {
            Post post = comment.getPost();
            if (post != null) {
                post.getComments().remove(comment);
            }
            commentRepository.deleteById(id);
        }
    }

    @Override
    public Comment findCommentById(int id) {
        return commentRepository.findById(id).orElse(null);
    }
}
