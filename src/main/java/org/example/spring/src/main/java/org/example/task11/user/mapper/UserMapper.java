package org.example.task11.user.mapper;

import org.example.task11.post.mapper.PostMapper;
import org.example.task11.user.User;
import org.example.task11.user.dto.UserRequestDTO;
import org.example.task11.user.dto.UserResponseDTO;
import org.example.task11.user.dto.UserWithPostsResponseDTO;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    public static User toEntity(UserRequestDTO dto) {
        return new User(dto.getName(), dto.getAge(), dto.getPassword());
    }

    public static UserResponseDTO toResponseDTO(User user) {
        return new UserResponseDTO(user.getId(), user.getName(), user.getAge());
    }

    public static List<UserResponseDTO> toResponseDTOList(List<User> users) {
        return users.stream().map(UserMapper::toResponseDTO).collect(Collectors.toList());
    }

    public static UserWithPostsResponseDTO toWithPostsResponseDTO(User user) {
        List<org.example.task11.post.dto.PostResponseDTO> posts =
                user.getPosts() == null ? Collections.emptyList() :
                        user.getPosts().stream().map(PostMapper::toResponseDTO).collect(Collectors.toList());

        return new UserWithPostsResponseDTO(user.getId(), user.getName(), user.getAge(), posts);
    }
}
