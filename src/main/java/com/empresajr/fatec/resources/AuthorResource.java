package com.empresajr.fatec.resources;

import com.empresajr.fatec.dto.author.request.AuthorDTO;
import com.empresajr.fatec.dto.author.response.AuthorNameAndEmailDTO;
import com.empresajr.fatec.services.AuthorService;
import com.empresajr.fatec.services.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/authors")
public class AuthorResource {

    @Autowired
    private AuthorService service;

    @Autowired
    private TopicService topicService;

    @GetMapping
    public ResponseEntity<List<AuthorDTO>> findAll(){
        List<AuthorDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/pages")
    public ResponseEntity<Page<AuthorNameAndEmailDTO>> findAllPaged(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "6") Integer linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
        Page<AuthorNameAndEmailDTO> list = service.findAllPaged(pageRequest);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AuthorNameAndEmailDTO> findById(@PathVariable Long id){
        AuthorNameAndEmailDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<AuthorNameAndEmailDTO> insert(@RequestBody AuthorNameAndEmailDTO dto) {
        dto = service.insert(dto);
        AuthorDTO authorDTO = new AuthorDTO(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("{id}")
                .buildAndExpand(authorDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<AuthorNameAndEmailDTO> update(@PathVariable Long id, @RequestBody AuthorNameAndEmailDTO dto){
        dto = service.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
