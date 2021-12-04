package com.empresajr.fatec.dto.internpost.request;

import com.empresajr.fatec.dto.author.request.AuthorDTO;
import com.empresajr.fatec.dto.interntopic.request.InternTopicDTO;
import com.empresajr.fatec.entities.Author;
import com.empresajr.fatec.entities.InternPost;
import com.empresajr.fatec.entities.InternTopic;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
public class InternPostDTO implements Serializable {
       private static final long serialVersionUID = 1L;

       private Long id;
       private String title;
       private Long internTopic_id;
       private String description;
       private String imgUrl;
       private Long author_id;

       public InternPostDTO(InternPost entity){
            id = entity.getId();
            title = entity.getTitle();
            description = entity.getDescription();
            imgUrl = entity.getImgUrl();
       }

       public InternPostDTO(InternPost entity, InternTopic top, Author aut){
              this(entity);
       }

       public InternPostDTO(InternPost entity, InternTopic top){
              this(entity);
       }

       public void setId(Long id) {
              this.id = id;
       }

       public void setTitle(String title) {
              this.title = title;
       }

       public void setTopic(Long internTopic_id) {
              this.internTopic_id = internTopic_id;
       }

       public void setDescription(String description) {
              this.description = description;
       }

       public void setImgUrl(String imgUrl) {
              this.imgUrl = imgUrl;
       }

       public void setAuthor(Long author_id){
              this.author_id = author_id;
       }

}
