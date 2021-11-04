package com.empresajr.fatec.services;

import com.empresajr.fatec.entities.Post;
import com.empresajr.fatec.entities.Topic;
import com.empresajr.fatec.entities.enums.TopicType;
import com.empresajr.fatec.repositories.PostRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository repository;

    public List<Post> findAll(){
        return repository.findAll();
    }

/*
    public List<Post> findPostByTitleAndTopic(String title, Topic topic){
        return repository.findPostByTitleAndTopic(title, topic);
    }

 */

}
