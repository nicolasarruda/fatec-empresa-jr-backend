package com.empresajr.fatec.services;

import com.empresajr.fatec.entities.Author;
import com.empresajr.fatec.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository repository;

    public List<Author> findAll(){
        return repository.findAll();
    }

    public Author findById(Long id){
        Optional<Author> obj = repository.findById(id);
        return obj.get();
    }
}
