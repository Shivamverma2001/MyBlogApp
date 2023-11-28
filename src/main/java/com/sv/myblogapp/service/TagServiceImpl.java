package com.sv.myblogapp.service;

import com.sv.myblogapp.entity.Post;
import com.sv.myblogapp.entity.Tag;
import com.sv.myblogapp.repository.PostRepository;
import com.sv.myblogapp.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private PostRepository postRepository;

    public TagServiceImpl() {
    }

    public TagServiceImpl(TagRepository tagRepository,PostRepository postRepository) {
        this.tagRepository = tagRepository;
        this.postRepository=postRepository;
    }

    @Override
    public void addTag(Tag tag) {
        tagRepository.save(tag);
    }

    @Override
    public List<Tag> findAllTags() {
        return tagRepository.findAll();
    }
}
