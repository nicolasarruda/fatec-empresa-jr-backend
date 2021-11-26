package com.empresajr.fatec.dto.interntopic.response;

import com.empresajr.fatec.dto.internpost.response.InternPostWithoutAuthorNameDTO;
import com.empresajr.fatec.dto.interntopic.request.InternTopicDTO;
import com.empresajr.fatec.entities.InternPost;
import com.empresajr.fatec.entities.InternTopic;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class InternTopicNameDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;

    private List<InternPostWithoutAuthorNameDTO> internPosts = new ArrayList<>();

    public InternTopicNameDTO(String name){
        this.name = name;
    }

    public InternTopicNameDTO(InternTopic entity){
         name = entity.getName();
    }

    public InternTopicNameDTO(InternTopic entity, List<InternPost> internPosts){
        this(entity);
        internPosts.forEach(p -> this.internPosts.add(new InternPostWithoutAuthorNameDTO(p)));
    }

    public InternTopicNameDTO(InternTopicDTO entity){
        name = entity.getName();
    }

    public void setName(String name) {
        this.name = name;
    }

}
