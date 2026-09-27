package org.example.task11.mapper;

import org.example.task11.model.Post;
import org.example.task11.dto.postdto.PostRequestDTO;
import org.example.task11.dto.postdto.PostResponseDTO;
import org.example.task11.dto.postdto.PostWithUserResponseDTO;

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
