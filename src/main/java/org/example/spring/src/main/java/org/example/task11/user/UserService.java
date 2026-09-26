package org.example.task11.user;

import org.example.task11.exception.ResourceNotFoundException;
import org.example.task11.post.dto.PostResponseDTO;
import org.example.task11.post.mapper.PostMapper;
import org.example.task11.user.dto.UserRequestDTO;
import org.example.task11.user.dto.UserResponseDTO;
import org.example.task11.user.dto.UserWithPostsResponseDTO;
import org.example.task11.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserResponseDTO createUser(UserRequestDTO dto) {
        User user = UserMapper.toEntity(dto);
        return UserMapper.toResponseDTO(userRepository.save(user));
    }

    public UserResponseDTO updateUser(Long id, UserRequestDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        user.setName(dto.getName());
        user.setAge(dto.getAge());
        user.setPassword(dto.getPassword());

        return UserMapper.toResponseDTO(userRepository.save(user));
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }

    public List<UserResponseDTO> getAllUsers() {
        return UserMapper.toResponseDTOList(userRepository.findAll());
    }

    public UserResponseDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        return UserMapper.toResponseDTO(user);
    }

    public List<PostResponseDTO> getUserPosts(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        return user.getPosts() == null ? Collections.emptyList() : PostMapper.toResponseDTOList(user.getPosts());
    }

    public List<UserWithPostsResponseDTO> getAllUsersWithPosts() {
        return userRepository.findAll().stream()
                .map(UserMapper::toWithPostsResponseDTO)
                .toList();
    }

    public UserWithPostsResponseDTO getUserWithPostsById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        return UserMapper.toWithPostsResponseDTO(user);
    }
}
