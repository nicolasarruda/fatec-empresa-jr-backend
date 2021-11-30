package com.empresajr.fatec.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
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
@Table(name = "tb_posts")
public class Post implements Serializable {
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

    @Column(columnDefinition = "TEXT")
    private String content;

    private String imgUrl;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    public Post(Long id, String title,Topic topic, String content, String imgUrl, Author author) {
        this.id = id;
        this.title = title;
        this.topic = topic;
        this.content = content;
        this.imgUrl = imgUrl;
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTopic(Topic topic){
        this.topic = topic;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setAuthor(Author author){
        this.author = author;
    }

    @PrePersist
    public void prePersist() {
        momentCreated = Instant.now();
    }

    @PreUpdate
    public void preUpdate() {
        momentUpdated = Instant.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return id.equals(post.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
