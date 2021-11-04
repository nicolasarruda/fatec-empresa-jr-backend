package com.empresajr.fatec.resources;

import com.empresajr.fatec.entities.InternTopic;
import com.empresajr.fatec.services.InternTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/intern-topics")
public class InternTopicResource {

    @Autowired
    private InternTopicService service;

    @GetMapping
    public ResponseEntity<List<InternTopic>> findAll(){
        List<InternTopic> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<InternTopic> findTopic(@PathVariable Long id) {
        InternTopic topic = service.findTopic(id);
        return ResponseEntity.ok().body(topic);
    }
}

