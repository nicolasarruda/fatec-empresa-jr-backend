package com.empresajr.fatec.services;

import com.empresajr.fatec.dto.author.response.AuthorNameAndEmailDTO;
import com.empresajr.fatec.dto.internpost.response.InternPostWithoutAuthorNameDTO;
import com.empresajr.fatec.dto.post.response.PostWithoutAuthorNameDTO;
import com.empresajr.fatec.entities.Author;
import com.empresajr.fatec.entities.InternPost;
import com.empresajr.fatec.entities.Post;
import com.empresajr.fatec.repositories.AuthorRepository;
import com.empresajr.fatec.repositories.InternPostRepository;
import com.empresajr.fatec.repositories.PostRepository;
import com.empresajr.fatec.services.exceptions.DatabaseException;
import com.empresajr.fatec.services.exceptions.InsertQueryException;
import com.empresajr.fatec.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private InternPostRepository internPostRepository;

    @Transactional(readOnly = true)
    public Page<AuthorNameAndEmailDTO> findAllPaged(PageRequest pageRequest){
        Page<Author> list = repository.findAll(pageRequest);
        return list.map(x -> new AuthorNameAndEmailDTO(x, x.getPosts(), x.getInternPosts()));
    }

    @Transactional(readOnly = true)
    public List<AuthorNameAndEmailDTO> findAll(){
        List<Author> list = repository.findAll();
        return list.stream().map(x -> new AuthorNameAndEmailDTO(x, x.getPosts(), x.getInternPosts())).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public AuthorNameAndEmailDTO findById(Long id){
        Optional<Author> obj = repository.findById(id);
        Author entity = obj.orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
        return new AuthorNameAndEmailDTO(entity, entity.getPosts(), entity.getInternPosts());
    }

    @Transactional
    public AuthorNameAndEmailDTO insert(AuthorNameAndEmailDTO dto) {
        Author entity = new Author();
        String email = dto.getEmail();
        existsByEmail(email);
        copyToDto(dto, entity);
        entity = repository.save(entity);
        return new AuthorNameAndEmailDTO(entity);
    }

    @Transactional
    public AuthorNameAndEmailDTO update(Long id,AuthorNameAndEmailDTO dto){
        try {
            Author entity = repository.getOne(id);
            copyToDto(dto, entity);
            entity = repository.save(entity);
            return new AuthorNameAndEmailDTO(entity);
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
        catch (DataIntegrityViolationException e){
            throw new DatabaseException("Violação de integridade no banco de dados");
        }
    }

    private void copyToDto(AuthorNameAndEmailDTO dto, Author entity) {
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());

        for(PostWithoutAuthorNameDTO postDto : dto.getPosts()){
            Post post = postRepository.getOne(postDto.getId());
            entity.getPosts().add(post);
        }
        for (InternPostWithoutAuthorNameDTO internPostDto : dto.getInternPosts()){
            InternPost internPost = internPostRepository.getOne(internPostDto.getId());
            entity.getInternPosts().add(internPost);
        }
    }

    private void existsByEmail(String email){
        if(repository.existsByEmail(email)) {
            throw new InsertQueryException("Erro na criação do autor: email " + email
                    + "já existente");
        }
    }
}
