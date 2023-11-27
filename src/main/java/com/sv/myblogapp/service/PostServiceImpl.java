package com.sv.myblogapp.service;

import com.sv.myblogapp.entity.Post;
import com.sv.myblogapp.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService{
    @Autowired
    private PostRepository postRepository;

    public PostServiceImpl(){}

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public void addPost(Post post) {
        postRepository.save(post);
    }

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public List<Post> findAllById(int id) {
        return postRepository.findAllById(Collections.singleton(id));
    }

    @Override
    public void deleteById(int id) {
        postRepository.deleteById(id);
    }
}