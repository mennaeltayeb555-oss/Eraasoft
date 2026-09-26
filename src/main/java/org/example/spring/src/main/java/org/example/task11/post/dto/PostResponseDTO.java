package org.example.task11.post.dto;

public class PostResponseDTO {
    private Long id;
    private String text;
    private String imagePath;
    private Long userId;

    public PostResponseDTO(Long id, String text, String imagePath, Long userId) {
        this.id = id;
        this.text = text;
        this.imagePath = imagePath;
        this.userId = userId;
    }

    public Long getId() { return id; }
    public String getText() { return text; }
    public String getImagePath() { return imagePath; }
    public Long getUserId() { return userId; }
}
