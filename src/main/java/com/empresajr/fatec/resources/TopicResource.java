package com.empresajr.fatec.resources;

import com.empresajr.fatec.dto.topic.request.TopicDTO;
import com.empresajr.fatec.dto.topic.response.TopicNameDTO;
import com.empresajr.fatec.services.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/topics")
public class TopicResource {

    @Autowired
    private TopicService service;

    @GetMapping
    public ResponseEntity<List<TopicNameDTO>> findAll(){
        List<TopicNameDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/pages")
    public ResponseEntity<Page<TopicNameDTO>> findAllPaged(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "6") Integer linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
        Page<TopicNameDTO> list = service.findAllPaged(pageRequest);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TopicNameDTO> findTopic(@PathVariable Long id) {
        TopicNameDTO topic = service.findTopic(id);
        return ResponseEntity.ok().body(topic);
    }

    @PostMapping
    public ResponseEntity<TopicNameDTO> insert(@RequestBody TopicDTO dto){
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("{id}")
                .buildAndExpand(dto.getId()).toUri();
        TopicNameDTO dtoResponse = new TopicNameDTO(dto);
        return ResponseEntity.created(uri).body(dtoResponse);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<TopicNameDTO> update(@PathVariable Long id, @RequestBody TopicNameDTO dto){
        dto = service.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

