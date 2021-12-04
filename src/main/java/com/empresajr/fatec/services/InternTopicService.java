package com.empresajr.fatec.services;

import com.empresajr.fatec.dto.internpost.response.InternPostWithoutAuthorNameDTO;
import com.empresajr.fatec.dto.interntopic.request.InternTopicDTO;
import com.empresajr.fatec.dto.interntopic.response.InternTopicNameDTO;
import com.empresajr.fatec.entities.InternPost;
import com.empresajr.fatec.entities.InternTopic;
import com.empresajr.fatec.repositories.InternPostRepository;
import com.empresajr.fatec.repositories.InternTopicRepository;
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
public class InternTopicService {

    @Autowired
    private InternTopicRepository repository;

    @Autowired
    private InternPostRepository internPostRepository;

    @Transactional(readOnly = true)
    public List<InternTopicNameDTO> findAll(){
        List<InternTopic> list = repository.findAll();
        return list.stream().map(x -> new InternTopicNameDTO(x, x.getInternPosts())).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Page<InternTopicNameDTO> findAllPaged(PageRequest pageRequest){
        Page<InternTopic> list = repository.findAll(pageRequest);
        return list.map(x -> new InternTopicNameDTO(x, x.getInternPosts()));
    }

    @Transactional(readOnly = true)
    public InternTopicNameDTO findInternTopic(Long id){
        Optional<InternTopic> obj = repository.findById(id);
        InternTopic entity = obj.orElseThrow(() -> new ResourceNotFoundException("Id não encontrado "));
        return new InternTopicNameDTO(entity, entity.getInternPosts());
    }


    @Transactional
    public InternTopicDTO insert(InternTopicDTO dto){
        InternTopic entity = new InternTopic();
        String name = dto.getName();
        existsByName(name);
        InternTopicNameDTO dtoInternTopicName = new InternTopicNameDTO(dto);
        copyToDto(entity, dtoInternTopicName);
        entity = repository.save(entity);
        return new InternTopicDTO(entity);
    }

    @Transactional
    public InternTopicNameDTO update(Long id, InternTopicNameDTO dto){
        try {
            InternTopic entity = repository.getOne(id);
            copyToDto(entity, dto);
            entity = repository.save(entity);
            return new InternTopicNameDTO(entity, entity.getInternPosts());
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

    private void copyToDto(InternTopic topic, InternTopicNameDTO dto) {
        topic.setName(dto.getName());

        for(InternPostWithoutAuthorNameDTO internPostDto : dto.getInternPosts()){
            InternPost post = internPostRepository.getOne(internPostDto.getId());
            topic.getInternPosts().add(post);
        }
    }

    private void existsByName(String name){
        if(repository.existsByName(name)) {
            throw new InsertQueryException("Erro na criação do tópico de estágio: tópico de estágio " + name
                    + "já existente");
        }
    }
}
