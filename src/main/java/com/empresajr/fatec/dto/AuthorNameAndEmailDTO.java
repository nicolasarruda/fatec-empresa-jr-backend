package com.empresajr.fatec.dto;

import com.empresajr.fatec.entities.Author;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
public class AuthorNameAndEmailDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String email;

    public AuthorNameAndEmailDTO(Author entity){
        name = entity.getName();
        email = entity.getEmail();
    }
}
