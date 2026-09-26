package org.example.task11.post;

import jakarta.validation.Valid;
import org.example.task11.post.dto.PostRequestDTO;
import org.example.task11.post.dto.PostResponseDTO;
import org.example.task11.post.dto.PostWithUserResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping
    public PostResponseDTO createPost(@Valid @RequestBody PostRequestDTO dto) {
        return postService.createPost(dto);
    }

    @GetMapping("/{id}")
    public PostResponseDTO getPostById(@PathVariable Long id) {
        return postService.getPostById(id);
    }

    @GetMapping
    public List<PostResponseDTO> getAllPosts() {
        return postService.getAllPosts();
    }

    @PutMapping("/{id}")
    public PostResponseDTO updatePost(@PathVariable Long id, @Valid @RequestBody PostRequestDTO dto) {
        return postService.updatePost(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.deletePost(id);
    }

    @GetMapping("/postsWithUsers")
    public List<PostWithUserResponseDTO> getAllPostsWithUsers() {
        return postService.getAllPostsWithUsers();
    }

    @GetMapping("/postWithUsers/{id}")
    public PostWithUserResponseDTO getPostWithUserById(@PathVariable Long id) {
        return postService.getPostWithUserById(id);
    }
}
