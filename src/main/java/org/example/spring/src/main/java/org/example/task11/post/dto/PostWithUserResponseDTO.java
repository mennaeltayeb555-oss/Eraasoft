package org.example.task11.post.dto;

import org.example.task11.user.dto.UserResponseDTO;

public class PostWithUserResponseDTO {
    private Long id;
    private String text;
    private String imagePath;
    private UserResponseDTO user;

    public PostWithUserResponseDTO(Long id, String text, String imagePath, UserResponseDTO user) {
        this.id = id;
        this.text = text;
        this.imagePath = imagePath;
        this.user = user;
    }

    public Long getId() { return id; }
    public String getText() { return text; }
    public String getImagePath() { return imagePath; }
    public UserResponseDTO getUser() { return user; }
}
