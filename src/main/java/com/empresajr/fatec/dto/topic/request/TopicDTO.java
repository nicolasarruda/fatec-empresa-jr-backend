package com.empresajr.fatec.dto.topic.request;

import com.empresajr.fatec.dto.post.response.PostWithoutAuthorNameDTO;
import com.empresajr.fatec.entities.Post;
import com.empresajr.fatec.entities.Topic;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class TopicDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;

    private List<PostWithoutAuthorNameDTO> posts = new ArrayList<>();

    public TopicDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public TopicDTO(Topic entity) {
        id = entity.getId();
        name = entity.getName();
    }

    public TopicDTO(Topic entity, List<Post> posts) {
        this(entity);
        posts.forEach(p -> this.posts.add(new PostWithoutAuthorNameDTO(p)));
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

}