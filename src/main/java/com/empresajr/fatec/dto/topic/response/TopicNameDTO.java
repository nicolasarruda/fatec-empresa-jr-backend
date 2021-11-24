package com.empresajr.fatec.dto.topic.response;

import com.empresajr.fatec.dto.post.response.PostWithoutAuthorNameDTO;
import com.empresajr.fatec.dto.topic.request.TopicDTO;
import com.empresajr.fatec.entities.Post;
import com.empresajr.fatec.entities.Topic;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class TopicNameDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;

    private List<PostWithoutAuthorNameDTO> posts = new ArrayList<>();

    public TopicNameDTO(String name){
        this.name = name;
    }

    public TopicNameDTO(Topic entity){
         name = entity.getName();
    }

    public TopicNameDTO(Topic entity, List<Post> posts){
        this(entity);
        posts.forEach(p -> this.posts.add(new PostWithoutAuthorNameDTO(p)));
    }

    public TopicNameDTO(TopicDTO entity){
        name = entity.getName();
    }

    public void setName(String name) {
        this.name = name;
    }

}
