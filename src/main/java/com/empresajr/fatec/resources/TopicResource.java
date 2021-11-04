package com.empresajr.fatec.resources;

import com.empresajr.fatec.entities.Topic;
import com.empresajr.fatec.services.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/topics")
public class TopicResource {

    @Autowired
    private TopicService service;

    @GetMapping
    public ResponseEntity<List<Topic>> findAll(){
        List<Topic> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<Topic> findTopic(@PathVariable Long id) {
        Topic topic1 = service.findTopic(id);
        return ResponseEntity.ok().body(topic1);
    }
}

