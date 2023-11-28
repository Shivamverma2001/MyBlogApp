package com.sv.myblogapp.repository;

import com.sv.myblogapp.entity.Post;
import com.sv.myblogapp.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag,Integer> {
}
