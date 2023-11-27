package com.sv.myblogapp.service;

import com.sv.myblogapp.entity.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {
    public void addPost(Post post);
    List<Post> findAll();
    List<Post> findAllById(int id);
    public void deleteById(int id);
}
