package com.sv.myblogapp.dao;

import com.sv.myblogapp.model.Post;

import java.util.*;

public interface PostDAO {
    public void save(Post post);
    public List<Post> showAllPosts();
}
