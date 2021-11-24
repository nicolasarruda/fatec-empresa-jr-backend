package com.empresajr.fatec.dto.topic.request;

import com.empresajr.fatec.entities.Topic;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
public class TopicDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;

    public TopicDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public TopicDTO(Topic entity){
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
