package com.empresajr.fatec.dto.interntopic.request;

import com.empresajr.fatec.entities.InternTopic;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
public class InternTopicDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;

    public InternTopicDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public InternTopicDTO(InternTopic entity){
         id = entity.getId();
         name = entity.getName();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
