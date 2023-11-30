package com.sv.myblogapp.service;

import com.sv.myblogapp.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    CommentRepository commentRepository;
    public CommentServiceImpl(CommentRepository commentRepository){
        this.commentRepository=commentRepository;
    }
}
