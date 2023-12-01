package com.sv.myblogapp.service;

import com.sv.myblogapp.entity.Post;
import com.sv.myblogapp.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public Post findById(int id) {
        return postRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(int id) {
        postRepository.deleteById(id);
    }

    @Override
    public void updateById(Post post) {
        postRepository.save(post);
    }

    @Override
    public Page<Post> findPaginated(int pageNo, int pageSize,String sortField,String sortDirection) {
        Sort sort=sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())?
                Sort.by(sortField).ascending():Sort.by(sortField).descending();
        Pageable pageable=PageRequest.of(pageNo-1,pageSize,sort);
        return this.postRepository.findAll(pageable);
    }
}