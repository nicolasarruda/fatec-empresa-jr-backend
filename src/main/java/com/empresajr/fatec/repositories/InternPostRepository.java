package com.empresajr.fatec.repositories;

import com.empresajr.fatec.entities.InternPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InternPostRepository extends JpaRepository<InternPost, Long> {

    //title = title.replaceAll(" ", "-");
    // evita que tenhamos duplicação no título. Bad request 401
    // @Query("SELECT title FROM Post p WHERE p.title LIKE ?")
    Optional<InternPost> findPostByTitle(String title);

}

