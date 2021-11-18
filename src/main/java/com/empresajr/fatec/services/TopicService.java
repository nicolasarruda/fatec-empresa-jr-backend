package com.empresajr.fatec.services;

import com.empresajr.fatec.entities.Topic;
import com.empresajr.fatec.repositories.TopicRepository;
import com.empresajr.fatec.services.exceptions.ResourceNotFoundException;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TopicService {

    @Autowired
    private TopicRepository repository;

    @Transactional(readOnly = true)
    public List<Topic> findAll(){
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Topic findTopic(Long id){
        Optional<Topic> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException("Id não encontrado "));
    }

}
