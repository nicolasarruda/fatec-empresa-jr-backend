package com.empresajr.fatec.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeId;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import javax.validation.constraints.NotEmpty;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Getter
@Entity
@NoArgsConstructor
public class Post implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonTypeId
    private Long id;

    @NotEmpty
    private String title;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Date publishDate;

    @Column(length = 5000)
    private String content;

    private String imgUrl;

    @OneToOne
    @MapsId
    private Topic topic;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;


    public Post(Long id, String title, Date publishDate, Topic topic, String content, String imgUrl, Author author) {
        this.id = id;
        this.title = title;
        this.publishDate = publishDate;
        this.topic = topic;
        this.content = content;
        this.imgUrl = imgUrl;
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setAuthor(Author author) {
        this.author = author;
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
