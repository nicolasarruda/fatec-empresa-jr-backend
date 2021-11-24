package com.empresajr.fatec.dto.author.response;

import com.empresajr.fatec.dto.post.response.PostWithoutAuthorNameDTO;
import com.empresajr.fatec.entities.Author;
import com.empresajr.fatec.entities.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class AuthorNameAndEmailDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String email;

    private List<PostWithoutAuthorNameDTO> posts = new ArrayList<>();

    public AuthorNameAndEmailDTO(Author entity){
        name = entity.getName();
        email = entity.getEmail();
    }

    public AuthorNameAndEmailDTO(Author entity, List<Post> posts){
        this(entity);
        posts.forEach(p -> this.posts.add((new PostWithoutAuthorNameDTO(p, p.getTopic()))));
    }

    public Long setId(Long id){
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPosts(List<PostWithoutAuthorNameDTO> posts) {
        this.posts = posts;
    }
}
