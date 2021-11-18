package com.empresajr.fatec.dto;

import com.empresajr.fatec.entities.Author;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
public class AuthorDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String email;

    public AuthorDTO(Author entity){
        id = entity.getId();
        name = entity.getName();
        email = entity.getEmail();
    }
}
