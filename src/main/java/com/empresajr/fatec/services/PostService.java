package com.empresajr.fatec.services;

import com.empresajr.fatec.entities.Post;
import com.empresajr.fatec.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository repository;

    public List<Post> findAll(){
        return repository.findAll();
    }

    public Post findById(String title){
        Optional<Post> obj = repository.findById(title);
        return obj.get();
    }

}
