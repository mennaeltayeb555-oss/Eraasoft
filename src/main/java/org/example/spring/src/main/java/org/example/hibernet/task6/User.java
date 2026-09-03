package org.example.hibernet.task6;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int age;


    // ==========================================
    // User -> UserDetails
    // One To One
    // ==========================================

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_details_id")
    private UserDetails userDetails;


    // ==========================================
    // User -> Friends
    // Many To Many
    // ==========================================

    @ManyToMany
    @JoinTable(
            name = "user_friend",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id")
    )
    private List<Friend> friends = new ArrayList<>();


    // ==========================================
    // User -> Posts
    // One To Many
    // ==========================================

    @OneToMany(mappedBy = "user")
    private List<Post> posts = new ArrayList<>();


    // ==========================================
    // Constructor
    // ==========================================

    public User() {
    }


    // ==========================================
    // Getters and Setters
    // ==========================================

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public List<Friend> getFriends() {
        return friends;
    }

    public List<Post> getPosts() {
        return posts;
    }


    // ==========================================
    // Helper Methods
    // ==========================================

    public void addFriend(Friend friend) {

        friends.add(friend);

        friend.getUsers().add(this);
    }


    public void addPost(Post post) {

        posts.add(post);

        post.setUser(this);
    }
}