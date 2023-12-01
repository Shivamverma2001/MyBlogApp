package com.sv.myblogapp.service;

import com.sv.myblogapp.entity.Post;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface PostService {
    public void addPost(Post post);
    List<Post> findAll();
    List<Post> findAllById(int id);
    Post findById(int id);
    public void deleteById(int id);
    public void updateById(Post post);
    public Page<Post> findPaginated(int pageNo,int pageSize,String sortField,String sortDirection);
}
