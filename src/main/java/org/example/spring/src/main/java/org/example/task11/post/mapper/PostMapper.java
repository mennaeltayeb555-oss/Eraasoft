package org.example.task11.post.mapper;

import org.example.task11.post.Post;
import org.example.task11.post.dto.PostRequestDTO;
import org.example.task11.post.dto.PostResponseDTO;
import org.example.task11.post.dto.PostWithUserResponseDTO;
import org.example.task11.user.mapper.UserMapper;

import java.util.List;
import java.util.stream.Collectors;

public class PostMapper {

    public static Post toEntity(PostRequestDTO dto) {
        return new Post(dto.getText(), dto.getImagePath());
    }

    public static PostResponseDTO toResponseDTO(Post post) {
        Long userId = post.getUser() != null ? post.getUser().getId() : null;
        return new PostResponseDTO(post.getId(), post.getText(), post.getImagePath(), userId);
    }

    public static List<PostResponseDTO> toResponseDTOList(List<Post> posts) {
        return posts.stream().map(PostMapper::toResponseDTO).collect(Collectors.toList());
    }

    public static PostWithUserResponseDTO toWithUserResponseDTO(Post post) {
        return new PostWithUserResponseDTO(
                post.getId(),
                post.getText(),
                post.getImagePath(),
                post.getUser() != null ? UserMapper.toResponseDTO(post.getUser()) : null
        );
    }
}
