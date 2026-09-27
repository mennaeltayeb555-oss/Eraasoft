package org.example.task11.serviceimpl;

import lombok.RequiredArgsConstructor;
import org.example.task11.dto.postdto.PostResponseDTO;
import org.example.task11.dto.userdto.UserRequestDTO;
import org.example.task11.dto.userdto.UserResponseDTO;
import org.example.task11.dto.userdto.UserWithPostsResponseDTO;
import org.example.task11.exception.ResourceNotFoundException;
import org.example.task11.mapper.PostMapper;
import org.example.task11.mapper.UserMapper;
import org.example.task11.model.User;
import org.example.task11.service.UserService;
import org.example.task11.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponseDTO createUser(UserRequestDTO dto) {
        User user = UserMapper.toEntity(dto);
        return UserMapper.toResponseDTO(userRepository.save(user));
    }

    @Override
    public UserResponseDTO updateUser(Long id, UserRequestDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        user.setName(dto.getName());
        user.setAge(dto.getAge());
        user.setPassword(dto.getPassword());

        return UserMapper.toResponseDTO(userRepository.save(user));
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        return UserMapper.toResponseDTOList(userRepository.findAll());
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        return UserMapper.toResponseDTO(user);
    }

    @Override
    public List<PostResponseDTO> getUserPosts(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        return user.getPosts() == null ? Collections.emptyList() : PostMapper.toResponseDTOList(user.getPosts());
    }

    @Override
    public List<UserWithPostsResponseDTO> getAllUsersWithPosts() {
        return userRepository.findAll().stream()
                .map(UserMapper::toWithPostsResponseDTO)
                .toList();
    }

    @Override
    public UserWithPostsResponseDTO getUserWithPostsById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        return UserMapper.toWithPostsResponseDTO(user);
    }
}
