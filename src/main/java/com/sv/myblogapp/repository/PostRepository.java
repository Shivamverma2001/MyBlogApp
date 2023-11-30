package com.sv.myblogapp.repository;

import com.sv.myblogapp.entity.Post;
import com.sv.myblogapp.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.*;
@Repository
public interface PostRepository extends JpaRepository<Post,Integer> {
//    @Query(value = "select DISTINCT p.* from posts p Left join post_tag pt on pt.post_id = p.id left join tags t on t.id = pt.tag_id "
//            ,nativeQuery = true)
//    List<Post> findAll();
}
