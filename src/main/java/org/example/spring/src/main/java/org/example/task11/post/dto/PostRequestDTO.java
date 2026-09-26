package org.example.task11.post.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PostRequestDTO {

    @NotBlank(message = "Text must not be null or empty")
    @Size(min = 20, message = "Text must be at least 20 characters")
    private String text;

    private String imagePath;

    private Long userId; // ، لربط البوست بيوزر موجود وقت الإنشاء

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public String getImagePath() { return imagePath; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
}
