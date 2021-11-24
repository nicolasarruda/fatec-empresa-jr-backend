package com.empresajr.fatec.dto.topic.response;

import com.empresajr.fatec.dto.topic.request.TopicDTO;
import com.empresajr.fatec.entities.Topic;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
public class TopicNameDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;

    public TopicNameDTO(String name){
        this.name = name;
    }

    public TopicNameDTO(Topic entity, Long id){
         name = entity.getName();
    }

    public Long getId(Topic entity){
        return entity.getId();
   }

    public TopicNameDTO(TopicDTO entity){
        name = entity.getName();
    }

    public void setName(String name) {
        this.name = name;
    }

}
