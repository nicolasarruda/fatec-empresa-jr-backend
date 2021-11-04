package com.empresajr.fatec.resources;

import com.empresajr.fatec.entities.Post;
import com.empresajr.fatec.entities.Topic;
import com.empresajr.fatec.entities.enums.TopicType;
import com.empresajr.fatec.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

    @Autowired
    private PostService service;

    @GetMapping
    public ResponseEntity<List<Post>> findAll(){
        List<Post> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

/*
    @GetMapping(value = "/find-titles")
    @ResponseBody
    public ResponseEntity<List<Post>> findPostByTitle(@RequestParam(value = "title", defaultValue = "") String title,
                                                @RequestParam(value = "topic", defaultValue = "") Topic topic) {
        title = URL.decodeParam(title);
        List<Post> post = service.findPostByTitleAndTopic(title, topic);

        return ResponseEntity.ok().body(post);
    }

 */
}

