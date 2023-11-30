package com.sv.myblogapp.service;

import com.sv.myblogapp.entity.Tag;

import java.util.List;

public interface TagService {
    public void addTag(Tag tag);
    public List<Tag> findAllTags();

    void updateTag(Tag tag);
}
