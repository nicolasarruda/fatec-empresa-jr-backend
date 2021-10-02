package com.empresajr.fatec.resources;

import com.empresajr.fatec.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @GetMapping
    public ResponseEntity<User> findAll(){
        User user = new User("Marcos", "marcos@mail.com", "123456", 1L);
        return ResponseEntity.ok().body(user);
    }
}
