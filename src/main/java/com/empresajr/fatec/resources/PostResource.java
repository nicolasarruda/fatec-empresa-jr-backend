package com.empresajr.fatec.resources;

import com.empresajr.fatec.dto.post.request.PostDTO;
import com.empresajr.fatec.dto.post.response.PostWithoutAuthorNameDTO;
import com.empresajr.fatec.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping(value = "/posts")
public class PostResource {

    @Autowired
    private PostService service;

    @Autowired
    private PostResource resource;

    @GetMapping
    public ResponseEntity<List<PostWithoutAuthorNameDTO>> findAll(){
        List<PostWithoutAuthorNameDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/pages")
    public ResponseEntity<Page<PostWithoutAuthorNameDTO>> findAllPaged(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "6") Integer linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "title") String orderBy) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        Page<PostWithoutAuthorNameDTO> list = service.findAllPaged(pageRequest);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PostWithoutAuthorNameDTO> findById(@PathVariable Long id) {
        PostWithoutAuthorNameDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    /*
    @GetMapping(value = "/find-titles")
    @ResponseBody
    public ResponseEntity<List<Post>> findPostByTitleAndTopic(@RequestParam(value = "title", defaultValue = "") String title,
                                                @RequestParam(value = "topic", defaultValue = "") String topic) {
        String encodedTitle = URL.decodeParameter(title);
        String encodedTopic = URL.decodeParameter(topic);

        List<Post> post = service.findByTitleAndTopic(encodedTitle, encodedTopic);

        return ResponseEntity.ok().body(post);
    }
     */

    @PostMapping
    public ResponseEntity<PostWithoutAuthorNameDTO> insert(@RequestBody PostDTO dto){
        dto = service.insert(dto);
        System.out.println("############# RESPONSE ##############");
        System.out.println(dto.getAuthor().getName());
        System.out.println(dto.getAuthor().getName());
        PostWithoutAuthorNameDTO response = new PostWithoutAuthorNameDTO(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("{id}")
                .buildAndExpand(response.getId()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PostWithoutAuthorNameDTO> update(@PathVariable Long id, @RequestBody PostDTO dto){
        dto = service.update(id, dto);
        PostWithoutAuthorNameDTO response = new PostWithoutAuthorNameDTO(dto);
        System.out.println("############# RESPONSE ##############");
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}

