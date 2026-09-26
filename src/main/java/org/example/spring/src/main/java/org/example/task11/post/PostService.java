package org.example.task11.post;


import org.example.task11.exception.ResourceNotFoundException;
import org.example.task11.post.dto.PostRequestDTO;
import org.example.task11.post.dto.PostResponseDTO;
import org.example.task11.post.dto.PostWithUserResponseDTO;
import org.example.task11.post.mapper.PostMapper;
import org.example.task11.user.User;
import org.example.task11.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    public PostResponseDTO createPost(PostRequestDTO dto) {
        Post post = PostMapper.toEntity(dto);

        if (dto.getUserId() != null) {
            User user = userRepository.findById(dto.getUserId())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + dto.getUserId()));
            post.setUser(user);
        }

        return PostMapper.toResponseDTO(postRepository.save(post));
    }

    public PostResponseDTO updatePost(Long id, PostRequestDTO dto) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + id));

        post.setText(dto.getText());
        post.setImagePath(dto.getImagePath());

        if (dto.getUserId() != null) {
            User user = userRepository.findById(dto.getUserId())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + dto.getUserId()));
            post.setUser(user);
        }

        return PostMapper.toResponseDTO(postRepository.save(post));
    }

    public void deletePost(Long id) {
        if (!postRepository.existsById(id)) {
            throw new ResourceNotFoundException("Post not found with id: " + id);
        }
        postRepository.deleteById(id);
    }

    public List<PostResponseDTO> getAllPosts() {
        return PostMapper.toResponseDTOList(postRepository.findAll());
    }

    public PostResponseDTO getPostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + id));
        return PostMapper.toResponseDTO(post);
    }

    public List<PostWithUserResponseDTO> getAllPostsWithUsers() {
        return postRepository.findAll().stream()
                .map(PostMapper::toWithUserResponseDTO)
                .toList();
    }

    public PostWithUserResponseDTO getPostWithUserById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + id));
        return PostMapper.toWithUserResponseDTO(post);
    }
}
