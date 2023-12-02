package com.sv.myblogapp.service;

import com.sv.myblogapp.entity.Post;
import com.sv.myblogapp.entity.Tag;
import com.sv.myblogapp.repository.PostRepository;
import com.sv.myblogapp.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService{
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private TagRepository tagRepository;

    public PostServiceImpl(){}

    public PostServiceImpl(PostRepository postRepository,TagRepository tagRepository) {
        this.postRepository = postRepository;
        this.tagRepository=tagRepository;
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

    @Override
    public Page<Post> searchByTagsPaginated(String tags,int pageNo,int pageSize, String sortField, String sortDir) {
        List<String> name = Arrays.asList(tags.split(","));
        System.out.println(name);
        PageRequest page = PageRequest.of(pageNo - 1, pageSize, Sort.by(Sort.Direction.fromString(sortDir), sortField));
        return postRepository.findByTagNamesIn(name, page);
    }
}