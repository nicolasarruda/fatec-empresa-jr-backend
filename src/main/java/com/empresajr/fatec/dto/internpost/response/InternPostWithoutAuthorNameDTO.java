package com.empresajr.fatec.dto.internpost.response;

import com.empresajr.fatec.dto.internpost.request.InternPostDTO;

import com.empresajr.fatec.entities.Author;
import com.empresajr.fatec.entities.InternPost;
import com.empresajr.fatec.entities.InternTopic;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
public class InternPostWithoutAuthorNameDTO implements Serializable {
       private static final long serialVersionUID = 1L;

       private Long id;
       private String title;
       private String internTopic;
       private String description;
       private String imgUrl;
       private String author;

       public InternPostWithoutAuthorNameDTO(InternPost entity) {
              id = entity.getId();
              title = entity.getTitle();
              internTopic = entity.getInternTopic().getName();
              description = entity.getDescription();
              imgUrl = entity.getImgUrl();
              author = entity.getAuthor().getName();
       }

       public InternPostWithoutAuthorNameDTO(InternPost entity, InternTopic top, Author aut) {
              this(entity);
              internTopic = top.getName();
              author = aut.getName();
       }

       public InternPostWithoutAuthorNameDTO(InternPost entity, InternTopic top) {
              this(entity);
       }

       public InternPostWithoutAuthorNameDTO(InternPostDTO dto) {
              id = dto.getId();
              title = dto.getTitle();
              internTopic = dto.getInternTopic().getName();
              description = dto.getDescription();
              imgUrl = dto.getImgUrl();
              author = dto.getAuthor().getName();
       }

       public void setId(Long id) {
              this.id = id;
       }

       public void setTitle(String title) {
              this.title = title;
       }

       public void setInternTopic(String internTopic) {
              this.internTopic = internTopic;
       }

       public void setDescription(String description) {
              this.description = description;
       }

       public void setImgUrl(String imgUrl) {
              this.imgUrl = imgUrl;
       }
}
