package com.empresajr.fatec.repositories;

import com.empresajr.fatec.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    // evita que tenhamos duplicação no título. Bad request 401

    /*
    @Query( " SELECT p.title, p.publishDate, p.content, p.imgUrl, p.author, t.name" +
            " FROM Post p INNER JOIN Topic t ON p.topic = t.id" +
            " WHERE LOWER(p.title) LIKE :title OR LOWER(t.name) LIKE :topic")
    List<Post> findByTitleIgnoreCaseOrTopicIgnoreCase(String title, String topic);

     */


}

