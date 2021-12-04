package com.empresajr.fatec.resources;

import com.empresajr.fatec.dto.interntopic.request.InternTopicDTO;
import com.empresajr.fatec.dto.interntopic.response.InternTopicNameDTO;
import com.empresajr.fatec.services.InternTopicService;
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
@RequestMapping(value = "/intern-topics")
public class InternTopicResource {

    @Autowired
    private InternTopicService service;

    @GetMapping
    public ResponseEntity<List<InternTopicNameDTO>> findAll(){
        List<InternTopicNameDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/pages")
    public ResponseEntity<Page<InternTopicNameDTO>> findAllPaged(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "6") Integer linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        Page<InternTopicNameDTO> list = service.findAllPaged(pageRequest);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<InternTopicNameDTO> findInternTopic(@PathVariable Long id) {
        InternTopicNameDTO topic = service.findInternTopic(id);
        return ResponseEntity.ok().body(topic);
    }

    @PostMapping
    public ResponseEntity<InternTopicNameDTO> insert(@RequestBody InternTopicDTO dto){
        InternTopicNameDTO response = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<InternTopicNameDTO> update(@PathVariable Long id, @RequestBody InternTopicDTO dto){
        InternTopicNameDTO response = service.update(id, dto);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

