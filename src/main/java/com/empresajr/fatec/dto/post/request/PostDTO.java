package com.empresajr.fatec.dto.post.request;

import com.empresajr.fatec.dto.author.request.AuthorDTO;
import com.empresajr.fatec.dto.post.response.PostWithoutAuthorNameDTO;
import com.empresajr.fatec.dto.topic.request.TopicDTO;
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
       private TopicDTO topic;
       private String content;
       private String imgUrl;
       private AuthorDTO author;

       public PostDTO(Post entity){
            id = entity.getId();
            title = entity.getTitle();
            content = entity.getContent();
            imgUrl = entity.getImgUrl();
       }

       public PostDTO(Post entity, Topic top, Author aut){
              this(entity);
              topic = new TopicDTO(top);
              author = new AuthorDTO(aut);
       }

       public PostDTO(Post entity, Topic top){
              this(entity);
              topic = new TopicDTO(top);
       }

       public PostDTO(PostWithoutAuthorNameDTO dto){
             AuthorDTO author = getAuthor();
       }

       public void setId(Long id) {
              this.id = id;
       }

       public void setTitle(String title) {
              this.title = title;
       }

       public void setTopic(TopicDTO topic) {
              this.topic = topic;
       }

       public void setContent(String content) {
              this.content = content;
       }

       public void setImgUrl(String imgUrl) {
              this.imgUrl = imgUrl;
       }

       public void setAuthor(AuthorDTO author){
              this.author = author;
       }

}
