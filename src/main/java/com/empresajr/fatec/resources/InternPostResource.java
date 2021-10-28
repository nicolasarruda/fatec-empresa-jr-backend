package com.empresajr.fatec.resources;

import com.empresajr.fatec.entities.InternPost;
import com.empresajr.fatec.services.InternPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/intern-posts")
public class InternPostResource {

    @Autowired
    private InternPostService service;

    @GetMapping
    public ResponseEntity<List<InternPost>> findAll(){
        List<InternPost> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/find-titles")
    @ResponseBody
    public ResponseEntity<InternPost> findPostByTitle(@RequestParam(value = "title") String title) {
        if(title == null){
            throw new NullPointerException("O título é um campo obrigatório");
        }

        InternPost internPost = service.findByTitle(title);

        return ResponseEntity.ok().body(internPost);
    }
}

