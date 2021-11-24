package com.empresajr.fatec.services;

import com.empresajr.fatec.dto.topic.request.TopicDTO;
import com.empresajr.fatec.entities.Topic;
import com.empresajr.fatec.repositories.TopicRepository;
import com.empresajr.fatec.services.exceptions.DatabaseException;
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

    @Transactional(readOnly = true)
    public List<TopicDTO> findAll(){
        List<Topic> list = repository.findAll();
        return list.stream().map(x -> new TopicDTO(x)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Page<TopicDTO> findAllPaged(PageRequest pageRequest){
        Page<Topic> list = repository.findAll(pageRequest);
        return list.map(x -> new TopicDTO(x));
    }

    @Transactional(readOnly = true)
    public TopicDTO findTopic(Long id){
        Optional<Topic> obj = repository.findById(id);
        Topic entity = obj.orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
        return new TopicDTO(entity);
    }

    @Transactional
    public TopicDTO insert(TopicDTO dto){
        Topic entity = new Topic();
        copyToDto(entity, dto);
        entity = repository.save(entity);
        return new TopicDTO(entity);
    }

    @Transactional
    public TopicDTO update(Long id, TopicDTO dto){
        try {
            Topic entity = repository.getOne(id);
            copyToDto(entity, dto);
            entity = repository.save(entity);
            return new TopicDTO(entity);
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
    }

}
