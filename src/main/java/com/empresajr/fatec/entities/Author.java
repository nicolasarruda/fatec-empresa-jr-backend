package com.empresajr.fatec.entities;

import com.empresajr.fatec.enums.Type;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Author extends Person implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Date publishDate;
    private Type type;


    public Author(String name, String email, String password, Long id, Date publishDate) {
        super(name, email, password);
        this.id = id;
        this.publishDate = publishDate;
    }

    public Long getId() {
        return id;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return id.equals(author.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
