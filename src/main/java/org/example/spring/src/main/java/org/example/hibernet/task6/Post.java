package org.example.hibernet.task6;
import jakarta.persistence.*;

@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String header;

    private String content;


    // ==========================================
    // Post -> User
    // Many To One
    // ==========================================

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    // ==========================================
    // Constructor
    // ==========================================

    public Post() {
    }


    // ==========================================
    // Getters and Setters
    // ==========================================

    public Long getId() {
        return id;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}