package com.empresajr.fatec.services;

import com.empresajr.fatec.entities.InternTopic;
import com.empresajr.fatec.repositories.InternTopicRepository;
import com.empresajr.fatec.services.exceptions.ResourceNotFoundException;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class InternTopicService {

    @Autowired
    private InternTopicRepository repository;

    @Transactional(readOnly = true)
    public List<InternTopic> findAll(){
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public InternTopic findTopic(Long id){
        Optional<InternTopic> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException("Id não encontrado "));
    }
}
