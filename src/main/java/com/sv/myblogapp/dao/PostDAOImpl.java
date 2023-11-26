package com.sv.myblogapp.dao;

import com.sv.myblogapp.model.Post;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostDAOImpl implements PostDAO{
    EntityManager entityManager;
    @Autowired
    public PostDAOImpl(EntityManager entityManager){
        this.entityManager=entityManager;
    }
    @Override
    @Transactional
    public void save(Post post) {
        entityManager.persist(post);
    }

    @Override
    public List<Post> showAllPosts() {
        TypedQuery<Post> posts=entityManager.createQuery("From Post", Post.class);
        return posts.getResultList();
    }
}