package com.empresajr.fatec.repositories;

import com.empresajr.fatec.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}

