package com.empresajr.fatec.services;

import com.empresajr.fatec.entities.Post;
import com.empresajr.fatec.entities.Topic;
import com.empresajr.fatec.entities.enums.TopicType;
import com.empresajr.fatec.repositories.PostRepository;
import com.empresajr.fatec.services.exceptions.ResourceNotFoundException;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository repository;

    @Transactional(readOnly = true)
    public List<Post> findAll(){
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Post findById(Long id){
        Optional<Post> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException("Id não encontrado "));
    }


    /*
    public List<Post> findByTitleAndTopic(String title, String topic){
        return repository.findByTitleIgnoreCaseOrTopicIgnoreCase(title, topic);
    }

     */




}
