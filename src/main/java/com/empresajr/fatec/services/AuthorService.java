package com.empresajr.fatec.services;

import com.empresajr.fatec.dto.AuthorDTO;
import com.empresajr.fatec.dto.AuthorNameAndEmailDTO;
import com.empresajr.fatec.entities.Author;
import com.empresajr.fatec.repositories.AuthorRepository;
import com.empresajr.fatec.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository repository;

    @Transactional(readOnly = true)
    public Page<AuthorNameAndEmailDTO> findAllPaged(PageRequest pageRequest){
        Page<Author> list = repository.findAll(pageRequest);
        return list.map(x -> new AuthorNameAndEmailDTO(x));
    }

    @Transactional(readOnly = true)
    public List<AuthorNameAndEmailDTO> findAll(){
        List<Author> list = repository.findAll();
        return list.stream().map(x -> new AuthorNameAndEmailDTO(x)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public AuthorNameAndEmailDTO findById(Long id){
        Optional<Author> obj = repository.findById(id);
        Author entity = obj.orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
        return new AuthorNameAndEmailDTO(entity);
    }

    /*
    @Transactional(readOnly = true)
    public AuthorNameAndEmailDTO findByEmailOrName(String name, String email){
        Optional<Author> obj = repository.findByEmailOrName(name, email);
        // do the filter here
        Author entity = obj.orElseThrow (() -> new ResourceNotFoundException("Recurso não encontrado"));
        return new AuthorNameAndEmailDTO(entity);
    }
     */

    @Transactional
    public AuthorDTO insert(AuthorDTO dto){
        Author entity = new Author();
        copyToDto(dto, entity);
        entity = repository.save(entity);
        return new AuthorDTO(entity);
    }

    @Transactional
    public AuthorDTO update(Long id,AuthorDTO dto){
        try {
            Author entity = repository.getOne(id);
            copyToDto(dto, entity);
            entity = repository.save(entity);
            return new AuthorDTO(entity);
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

    private void copyToDto(AuthorDTO dto, Author entity) {
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
    }
}
