package com.empresajr.fatec.services;

import com.empresajr.fatec.dto.post.request.PostDTO;
import com.empresajr.fatec.dto.post.response.PostWithoutAuthorNameDTO;
import com.empresajr.fatec.entities.Author;
import com.empresajr.fatec.entities.Post;
import com.empresajr.fatec.entities.Topic;
import com.empresajr.fatec.repositories.AuthorRepository;
import com.empresajr.fatec.repositories.PostRepository;
import com.empresajr.fatec.repositories.TopicRepository;
import com.empresajr.fatec.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
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
public class PostService {

    @Autowired
    private PostRepository repository;

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private TopicService topicService;

    @Autowired
    private AuthorRepository authorRepository;

    @Transactional(readOnly = true)
    public List<PostWithoutAuthorNameDTO> findAll(){
        List<Post> list = repository.findAll();
        return list.stream().map(x -> new PostWithoutAuthorNameDTO(x, x.getTopic(), x.getAuthor())).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Page<PostWithoutAuthorNameDTO> findAllPaged(PageRequest pageRequest){
        Page<Post> list = repository.findAll(pageRequest);
        return list.map(x -> new PostWithoutAuthorNameDTO(x, x.getTopic(), x.getAuthor()));
    }

    @Transactional(readOnly = true)
    public PostWithoutAuthorNameDTO findById(Long id){
        Optional<Post> obj = repository.findById(id);
        Post entity = obj.orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
        return new PostWithoutAuthorNameDTO(entity);
    }

    @Transactional
    public PostDTO insert(PostDTO dto){
        Post entity = new Post();
        copyToDto(dto, entity);
        entity = repository.save(entity);
        return new PostDTO(entity, entity.getTopic(), entity.getAuthor());
    }

    @Transactional
    public PostDTO update(Long id, PostDTO dto) {
        try {
            Post entity = repository.getOne(id);
            copyToDto(dto, entity);
            entity = repository.save(entity);
            return new PostDTO(entity, entity.getTopic(), entity.getAuthor());
        }
        catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Id não encontrado" + id);
        }
    }

    public void delete(Long id){
        try {
            repository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException("Id não encontrado" + id);
        }
    }

    private void copyToDto(PostDTO dto, Post entity) {
        entity.setTitle(dto.getTitle());
        entity.setContent(dto.getContent());
        entity.setImgUrl(dto.getImgUrl());

        Topic topic = topicRepository.getOne(dto.getTopic().getId());
        entity.setTopic(topic);
        Author author = authorRepository.getOne(dto.getAuthor().getId());
        entity.setAuthor(author);
    }
}





