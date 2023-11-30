package com.sv.myblogapp.repository;

import com.sv.myblogapp.entity.Post;
import com.sv.myblogapp.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.*;
@Repository
public interface PostRepository extends JpaRepository<Post,Integer> {
}
