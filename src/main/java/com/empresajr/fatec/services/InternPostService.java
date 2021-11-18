package com.empresajr.fatec.services;

import com.empresajr.fatec.entities.InternPost;
import com.empresajr.fatec.repositories.InternPostRepository;
import com.empresajr.fatec.services.exceptions.ResourceNotFoundException;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class InternPostService {

    @Autowired
    private InternPostRepository repository;

    @Transactional(readOnly = true)
    public List<InternPost> findAll(){
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public InternPost findById(Long id){
        Optional<InternPost> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException("Id não encontrado "));
    }

}
