package com.sv.myblogapp.service;

import com.sv.myblogapp.entity.Comment;

public interface CommentService {
    public void add(Comment comment);
    public void deleteById(int id);
    public Comment findCommentById(int id);
}
