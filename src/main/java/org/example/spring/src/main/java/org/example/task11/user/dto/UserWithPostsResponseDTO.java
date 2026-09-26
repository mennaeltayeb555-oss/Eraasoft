package org.example.task11.user.dto;

import org.example.task11.post.dto.PostResponseDTO;

import java.util.List;

public class UserWithPostsResponseDTO {
    private Long id;
    private String name;
    private Integer age;
    private List<PostResponseDTO> posts;

    public UserWithPostsResponseDTO(Long id, String name, Integer age, List<PostResponseDTO> posts) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.posts = posts;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public Integer getAge() { return age; }
    public List<PostResponseDTO> getPosts() { return posts; }
}
