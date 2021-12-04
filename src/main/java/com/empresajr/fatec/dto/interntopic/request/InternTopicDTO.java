package com.empresajr.fatec.dto.interntopic.request;

import com.empresajr.fatec.dto.internpost.response.InternPostWithoutAuthorNameDTO;
import com.empresajr.fatec.entities.InternPost;
import com.empresajr.fatec.entities.InternTopic;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class InternTopicDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;

    private List<InternPostWithoutAuthorNameDTO> internPosts = new ArrayList<>();

    public InternTopicDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public InternTopicDTO(InternTopic entity){
         id = entity.getId();
         name = entity.getName();
    }

    public InternTopicDTO(InternTopic entity, List<InternPost> internPosts){
        this(entity);
        internPosts.forEach(p -> this.internPosts.add(new InternPostWithoutAuthorNameDTO(p)));
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
