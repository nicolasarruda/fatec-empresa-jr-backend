package com.empresajr.fatec.repositories;

import com.empresajr.fatec.entities.Post;
import com.empresajr.fatec.entities.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    //title = title.replaceAll(" ", "-");
    // evita que tenhamos duplicação no título. Bad request 401
    // @Query("SELECT title FROM Post p WHERE p.title LIKE ?")
    // List<Post> findPostByTitleAndTopic(String title, Topic topic);

}

