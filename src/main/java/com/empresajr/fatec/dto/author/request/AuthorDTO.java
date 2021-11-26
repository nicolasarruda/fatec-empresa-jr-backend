package com.empresajr.fatec.dto.author.request;

import com.empresajr.fatec.dto.author.response.AuthorNameAndEmailDTO;
import com.empresajr.fatec.dto.internpost.response.InternPostWithoutAuthorNameDTO;
import com.empresajr.fatec.dto.post.response.PostWithoutAuthorNameDTO;
import com.empresajr.fatec.entities.Author;
import com.empresajr.fatec.entities.InternPost;
import com.empresajr.fatec.entities.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class AuthorDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String email;

    private List<PostWithoutAuthorNameDTO> posts = new ArrayList<>();

    private List<InternPostWithoutAuthorNameDTO> internPosts = new ArrayList<>();

    public AuthorDTO(Long id, String name, String email){
        this.id =id;
        this.name = name;
        this.email = email;
    }

    public AuthorDTO(Author entity){
        id = entity.getId();
        name = entity.getName();
        email = entity.getEmail();
    }

    public AuthorDTO(Author entity, List<Post> posts, List<InternPost> internPosts){
        this(entity);
        posts.forEach(p -> this.posts.add((new PostWithoutAuthorNameDTO(p, p.getTopic()))));
        internPosts.forEach(p -> this.internPosts.add(new InternPostWithoutAuthorNameDTO(p, p.getInternTopic())));
    }

    public AuthorDTO(AuthorNameAndEmailDTO entity){
        entity.setId(id);
        name = entity.getName();
        email = entity.getEmail();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setListPosts(List<PostWithoutAuthorNameDTO> posts) {
        this.posts = posts;
    }

    public void setInternPosts(List<InternPostWithoutAuthorNameDTO> internPosts){
        this.internPosts = internPosts;
    }
}
