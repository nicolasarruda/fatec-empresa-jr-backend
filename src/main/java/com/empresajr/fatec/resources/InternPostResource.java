package com.empresajr.fatec.resources;

import com.empresajr.fatec.dto.internpost.request.InternPostDTO;
import com.empresajr.fatec.dto.internpost.response.InternPostWithoutAuthorNameDTO;
import com.empresajr.fatec.dto.post.request.PostDTO;
import com.empresajr.fatec.entities.InternPost;
import com.empresajr.fatec.services.InternPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;


import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/intern-posts")
public class InternPostResource {

    @Autowired
    private InternPostService service;

    @GetMapping
    public ResponseEntity<List<InternPostWithoutAuthorNameDTO>> findAll(){
        List<InternPostWithoutAuthorNameDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/pages")
    public ResponseEntity<Page<InternPostWithoutAuthorNameDTO>> findAllPaged(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "6") Integer linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "title") String orderBy) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        Page<InternPostWithoutAuthorNameDTO> list = service.findAllPaged(pageRequest);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<InternPostWithoutAuthorNameDTO> findById(@PathVariable Long id) {
        InternPostWithoutAuthorNameDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<InternPostWithoutAuthorNameDTO> insert(@RequestBody InternPostDTO dto){
        dto = service.insert(dto);
        System.out.println("############# RESPONSE ##############");
        System.out.println(dto.getAuthor().getName());
        System.out.println(dto.getAuthor().getName());
        InternPostWithoutAuthorNameDTO response = new InternPostWithoutAuthorNameDTO(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("{id}")
                .buildAndExpand(response.getId()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<InternPostWithoutAuthorNameDTO> update(@PathVariable Long id, @RequestBody InternPostDTO dto){
        dto = service.update(id, dto);
        InternPostWithoutAuthorNameDTO response = new InternPostWithoutAuthorNameDTO(dto);
        System.out.println("############# RESPONSE ##############");
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

