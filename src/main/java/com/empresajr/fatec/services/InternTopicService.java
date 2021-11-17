package com.empresajr.fatec.services;

import com.empresajr.fatec.entities.InternTopic;
import com.empresajr.fatec.repositories.InternTopicRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InternTopicService {

    @Autowired
    private InternTopicRepository repository;

    public List<InternTopic> findAll(){
        return repository.findAll();
    }

    public InternTopic findTopic(Long id){
        Optional<InternTopic> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Tópico não encontrado! Tópico: ", "Erro"));
    }
}
