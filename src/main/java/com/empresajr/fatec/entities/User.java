package com.empresajr.fatec.entities;

import com.empresajr.fatec.enums.Type;

import java.io.Serializable;
import java.util.Objects;

public class User extends Person implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Type type;


    public User(String name, String email, String password, Long id) {
        super(name, email, password);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User author = (User) o;
        return id.equals(author.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
