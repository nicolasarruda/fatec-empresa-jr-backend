package com.empresajr.fatec.services;

import com.empresajr.fatec.dto.internpost.request.InternPostDTO;
import com.empresajr.fatec.dto.internpost.response.InternPostWithoutAuthorNameDTO;
import com.empresajr.fatec.entities.Author;
import com.empresajr.fatec.entities.InternPost;
import com.empresajr.fatec.entities.InternTopic;
import com.empresajr.fatec.repositories.AuthorRepository;
import com.empresajr.fatec.repositories.InternPostRepository;
import com.empresajr.fatec.repositories.InternTopicRepository;
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
public class InternPostService {

    @Autowired
    private InternPostRepository repository;

    @Autowired
    private InternTopicRepository internTopicRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Transactional(readOnly = true)
    public List<InternPostWithoutAuthorNameDTO> findAll(){
        List<InternPost> list = repository.findAll();
        return list.stream().map(x -> new InternPostWithoutAuthorNameDTO(x, x.getInternTopic(), x.getAuthor())).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Page<InternPostWithoutAuthorNameDTO> findAllPaged(PageRequest pageRequest){
        Page<InternPost> list = repository.findAll(pageRequest);
        return list.map(x -> new InternPostWithoutAuthorNameDTO(x, x.getInternTopic(), x.getAuthor()));
    }

    @Transactional(readOnly = true)
    public InternPostWithoutAuthorNameDTO findById(Long id){
        Optional<InternPost> obj = repository.findById(id);
        InternPost entity = obj.orElseThrow(() -> new ResourceNotFoundException("Id não encontrado "));
        return new InternPostWithoutAuthorNameDTO(entity);
    }

    @Transactional
    public InternPostWithoutAuthorNameDTO insert(InternPostDTO dto){
        InternPost entity = new InternPost();
        insertAuthorToDto(dto, entity);
        copyToDto(dto, entity);
        entity = repository.save(entity);
        return new InternPostWithoutAuthorNameDTO(entity, entity.getInternTopic(), entity.getAuthor());
    }

    @Transactional
    public InternPostWithoutAuthorNameDTO update(Long id, InternPostDTO dto) {
        try {
            InternPost entity = repository.getById(id);
            copyToDto(dto, entity);
            entity = repository.save(entity);
            return new InternPostWithoutAuthorNameDTO(entity, entity.getInternTopic(), entity.getAuthor());
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

    private void copyToDto(InternPostDTO dto, InternPost entity) {
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setImgUrl(dto.getImgUrl());

        InternTopic topic = internTopicRepository.getById(dto.getInternTopic_id());
        entity.setInternTopic(topic);
    }

    private void insertAuthorToDto(InternPostDTO dto, InternPost entity){
        Author author = authorRepository.getById(dto.getAuthor_id());
        entity.setAuthor(author);
    }
}
