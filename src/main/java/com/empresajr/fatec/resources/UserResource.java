package com.empresajr.fatec.resources;

import com.empresajr.fatec.entities.User;
import com.empresajr.fatec.entities.enums.Type;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @GetMapping
    public ResponseEntity<User> findAll(){

        User user1 = new User(1L, "Marcos", "marcos@mail.com", "123456", Type.USER);
        return ResponseEntity.ok().body(user1);

    }
}
