package org.example.task11.serviceimpl;

import lombok.RequiredArgsConstructor;
import org.example.task11.dto.postdto.PostRequestDTO;
import org.example.task11.dto.postdto.PostResponseDTO;
import org.example.task11.dto.postdto.PostWithUserResponseDTO;
import org.example.task11.exception.ResourceNotFoundException;
import org.example.task11.mapper.PostMapper;
import org.example.task11.model.Post;
import org.example.task11.service.PostService;
import org.example.task11.model.User;
import org.example.task11.repository.PostRepository;
import org.example.task11.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Override
    public PostResponseDTO createPost(PostRequestDTO dto) {
        Post post = PostMapper.toEntity(dto);

        if (dto.getUserId() != null) {
            User user = userRepository.findById(dto.getUserId())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + dto.getUserId()));
            post.setUser(user);
        }

        return PostMapper.toResponseDTO(postRepository.save(post));
    }

    @Override
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

    @Override
    public void deletePost(Long id) {
        if (!postRepository.existsById(id)) {
            throw new ResourceNotFoundException("Post not found with id: " + id);
        }
        postRepository.deleteById(id);
    }

    @Override
    public List<PostResponseDTO> getAllPosts() {
        return PostMapper.toResponseDTOList(postRepository.findAll());
    }

    @Override
    public PostResponseDTO getPostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + id));
        return PostMapper.toResponseDTO(post);
    }

    @Override
    public List<PostWithUserResponseDTO> getAllPostsWithUsers() {
        return postRepository.findAll().stream()
                .map(PostMapper::toWithUserResponseDTO)
                .toList();
    }

    @Override
    public PostWithUserResponseDTO getPostWithUserById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + id));
        return PostMapper.toWithUserResponseDTO(post);
    }
}
