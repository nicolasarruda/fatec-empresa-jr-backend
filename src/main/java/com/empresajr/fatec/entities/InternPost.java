package com.empresajr.fatec.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
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
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import java.io.Serializable;
import java.util.Date;
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
    @GeneratedValue(generator = "title")
    private String title;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Date moment;

    @JsonIgnore
    @OneToOne
    @MapsId
    private InternTopic internTopic;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @Column(length = 5000)
    private String description;
    private String imgUrl;

    public InternPost(Long id, String title,InternTopic internTopic, Date moment, String description, String imgUrl, Author author) {
        this.id = id;
        this.title = title;
        this.internTopic = internTopic;
        this.moment = moment;
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

    public void setMoment(Date moment) {
        this.moment = moment;
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
