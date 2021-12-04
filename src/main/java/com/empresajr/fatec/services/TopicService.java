package com.empresajr.fatec.services;

import com.empresajr.fatec.dto.post.response.PostWithoutAuthorNameDTO;
import com.empresajr.fatec.dto.topic.request.TopicDTO;
import com.empresajr.fatec.dto.topic.response.TopicNameDTO;
import com.empresajr.fatec.entities.Post;
import com.empresajr.fatec.entities.Topic;
import com.empresajr.fatec.repositories.PostRepository;
import com.empresajr.fatec.repositories.TopicRepository;
import com.empresajr.fatec.services.exceptions.DatabaseException;
import com.empresajr.fatec.services.exceptions.InsertQueryException;
import com.empresajr.fatec.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TopicService {

    @Autowired
    private TopicRepository repository;

    @Autowired
    private PostRepository postRepository;

    @Transactional(readOnly = true)
    public List<TopicNameDTO> findAll(){
        List<Topic> list = repository.findAll();
        return list.stream().map(x -> new TopicNameDTO(x, x.getPosts())).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Page<TopicNameDTO> findAllPaged(PageRequest pageRequest){
        Page<Topic> list = repository.findAll(pageRequest);
        return list.map(x -> new TopicNameDTO(x, x.getPosts()));
    }

    @Transactional(readOnly = true)
    public TopicNameDTO findTopic(Long id){
        Optional<Topic> obj = repository.findById(id);
        Topic entity = obj.orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
        return new TopicNameDTO(entity, entity.getPosts());
    }

    @Transactional
    public TopicNameDTO insert(TopicDTO dto){
        Topic entity = new Topic();
        String name = dto.getName();
        existsByName(name);
        copyToDto(entity, dto);
        entity = repository.save(entity);
        return new TopicNameDTO(entity);
    }

    @Transactional
    public TopicNameDTO update(Long id, TopicDTO dto){
        try {
            Topic entity = repository.getById(id);
            copyToDto(entity, dto);
            entity = repository.save(entity);
            return new TopicNameDTO(entity, entity.getPosts());
        }
        catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Id não encontrado " + id);
        }
    }

    public void delete(Long id){
        try {
            repository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException("Id não encontrado " +id);
        }
        catch (DataIntegrityViolationException e){
            throw new DatabaseException("Violação de integridade no banco de dados");
        }
    }

    private void copyToDto(Topic topic, TopicDTO dto) {
        topic.setName(dto.getName());

        for(PostWithoutAuthorNameDTO postDto : dto.getPosts()){
            Post post = postRepository.getById(postDto.getId());
            topic.getPosts().add(post);
        }
    }

    private void existsByName(String name){
        if(repository.existsByName(name)) {
            throw new InsertQueryException("Erro na criação do tópico: tópico " + name
                    + "já existente");
        }
    }
}
