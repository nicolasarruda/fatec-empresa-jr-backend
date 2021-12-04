package com.empresajr.fatec.dto.post.request;

import com.empresajr.fatec.entities.Author;
import com.empresajr.fatec.entities.Post;
import com.empresajr.fatec.entities.Topic;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
public class PostDTO implements Serializable {
       private static final long serialVersionUID = 1L;

       private Long id;
       private String title;
       private Long topic_id;
       private String content;
       private String imgUrl;
       private Long author_id;

       public PostDTO(Post entity){
            id = entity.getId();
            title = entity.getTitle();
            content = entity.getContent();
            imgUrl = entity.getImgUrl();
       }

       public PostDTO(Post entity, Topic top, Author aut){
              this(entity);
       }

       public PostDTO(Post entity, Topic top){
              this(entity);
       }

       public void setId(Long id) {
              this.id = id;
       }

       public void setTitle(String title) {
              this.title = title;
       }

       public void setTopic(Long topic_id) {
              this.topic_id = topic_id;
       }

       public void setContent(String content) {
              this.content = content;
       }

       public void setImgUrl(String imgUrl) {
              this.imgUrl = imgUrl;
       }

       public void setAuthor(Long author_id){
              this.author_id = author_id;
       }

}
