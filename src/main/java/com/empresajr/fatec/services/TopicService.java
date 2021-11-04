package com.empresajr.fatec.services;

import com.empresajr.fatec.entities.Topic;
import com.empresajr.fatec.repositories.TopicRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopicService {

    @Autowired
    private TopicRepository repository;

    public List<Topic> findAll(){
        return repository.findAll();
    }

    public Topic findTopic(Long id){
        Optional<Topic> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Tópico não encontrado! Tópico: ", "Erro"));
    }

}
