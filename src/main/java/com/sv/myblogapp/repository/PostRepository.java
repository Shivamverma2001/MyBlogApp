package com.sv.myblogapp.repository;

import com.sv.myblogapp.entity.Post;
import com.sv.myblogapp.entity.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.*;
@Repository
public interface PostRepository extends JpaRepository<Post,Integer> {
    @Query("SELECT p FROM Post p JOIN p.tags t WHERE t.name IN :tagNames")
    Page<Post> findByTagNamesIn(@Param("tagNames") List<String> tagNames, Pageable pageable);

    Page<Post> findByTitleContainingOrAuthorContainingOrExcerptContainingOrContentContaining(
            String title, String author, String excerpt, String content, Pageable pageable);
}
