package org.example.task11.service;

import org.example.task11.dto.postdto.PostResponseDTO;
import org.example.task11.dto.userdto.UserRequestDTO;
import org.example.task11.dto.userdto.UserResponseDTO;
import org.example.task11.dto.userdto.UserWithPostsResponseDTO;

import java.util.List;

public interface UserService {

    UserResponseDTO createUser(UserRequestDTO dto);

    UserResponseDTO updateUser(Long id, UserRequestDTO dto);

    void deleteUser(Long id);

    List<UserResponseDTO> getAllUsers();

    UserResponseDTO getUserById(Long id);

    List<PostResponseDTO> getUserPosts(Long id);

    List<UserWithPostsResponseDTO> getAllUsersWithPosts();

    UserWithPostsResponseDTO getUserWithPostsById(Long id);
}