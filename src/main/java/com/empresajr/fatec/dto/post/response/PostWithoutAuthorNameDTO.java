package com.empresajr.fatec.dto.post.response;

import com.empresajr.fatec.dto.author.request.AuthorDTO;
import com.empresajr.fatec.dto.post.request.PostDTO;
import com.empresajr.fatec.dto.topic.request.TopicDTO;
import com.empresajr.fatec.entities.Author;
import com.empresajr.fatec.entities.Post;
import com.empresajr.fatec.entities.Topic;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.io.Serializable;

@Getter
@NoArgsConstructor
public class PostWithoutAuthorNameDTO implements Serializable {
       private static final long serialVersionUID = 1L;

       private Long id;
       private String title;
       private String topic;
       private String content;
       private String imgUrl;
       private String author;

       public PostWithoutAuthorNameDTO(Post entity){
            id = entity.getId();
            title = entity.getTitle();
            topic = entity.getTopic().getName();
            content = entity.getContent();
            imgUrl = entity.getImgUrl();
            author = entity.getAuthor().getName();
       }

       /*
       public PostWithoutAuthorNameDTO(Page<PostDTO> list){
              for (PostDTO postDTO : list) {
                     list.map(x -> new PostWithoutAuthorNameDTO(x));
              }

       }

        */

       public PostWithoutAuthorNameDTO(Post entity, Topic top, Author aut){
              this(entity);
              topic = top.getName();
              author = aut.getName();
       }

       public PostWithoutAuthorNameDTO(Post entity, Topic top){
              this(entity);
       }

       public PostWithoutAuthorNameDTO(PostDTO dto){
              id = dto.getId();
              title = dto.getTitle();
              topic = dto.getTopic().getName();
              content = dto.getContent();
              imgUrl = dto.getImgUrl();
              author = dto.getAuthor().getName();
       }



       public void setId(Long id) {
              this.id = id;
       }

       public void setTitle(String title) {
              this.title = title;
       }

       public void setTopic(String topic) {
              this.topic = topic;
       }

       public void setContent(String content) {
              this.content = content;
       }

       public void setImgUrl(String imgUrl) {
              this.imgUrl = imgUrl;
       }


}
