package com.empresajr.fatec.resources;

import com.empresajr.fatec.entities.Post;
import com.empresajr.fatec.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping(value = "/{title}")
    public ResponseEntity<Post> findById(@PathVariable String title){
        Post post = service.findById(title);
        return ResponseEntity.ok().body(post);
    }
}
