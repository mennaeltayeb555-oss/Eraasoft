package org.example.task11.post;

import jakarta.persistence.*;
import org.example.task11.user.User;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String text;
    private String imagePath;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Post() {}

    public Post(String text, String imagePath) {
        this.text = text;
        this.imagePath = imagePath;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public String getImagePath() { return imagePath; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
