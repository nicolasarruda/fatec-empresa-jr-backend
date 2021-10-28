package com.empresajr.fatec.services;

import com.empresajr.fatec.entities.InternPost;
import com.empresajr.fatec.repositories.InternPostRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InternPostService {

    @Autowired
    private InternPostRepository repository;

    public List<InternPost> findAll(){
        return repository.findAll();
    }

    public InternPost findByTitle(String title){
        Optional<InternPost> obj = repository.findPostByTitle(title);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Título não encontrado! Título: ", title));
    }

}
