package com.empresajr.fatec.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "tb_internPost")
public class InternPost implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String title;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant momentCreated;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant momentUpdated;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "intern_topic_id")
    private InternTopic internTopic;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @Column(columnDefinition = "TEXT")
    private String description;
    private String imgUrl;

    public InternPost(Long id, String title,InternTopic internTopic, String description, String imgUrl, Author author) {
        this.id = id;
        this.title = title;
        this.internTopic = internTopic;
        this.description = description;
        this.imgUrl = imgUrl;
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setInternTopic(InternTopic internTopic){
        this.internTopic = internTopic;
    }

    @PrePersist
    public void prePersist(){
        momentCreated = Instant.now();
    }

    @PreUpdate
    public void preUpdate(){
        momentUpdated = Instant.now();
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setAuthor(Author author){
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InternPost that = (InternPost) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
