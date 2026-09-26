package org.example.task11.user;

import jakarta.validation.Valid;
import org.example.task11.post.dto.PostResponseDTO;
import org.example.task11.user.dto.UserRequestDTO;
import org.example.task11.user.dto.UserResponseDTO;
import org.example.task11.user.dto.UserWithPostsResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public UserResponseDTO createUser(@Valid @RequestBody UserRequestDTO dto) {
        return userService.createUser(dto);
    }

    @GetMapping("/{id}")
    public UserResponseDTO getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping
    public List<UserResponseDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("/{id}")
    public UserResponseDTO updateUser(@PathVariable Long id, @Valid @RequestBody UserRequestDTO dto) {
        return userService.updateUser(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @GetMapping("/{id}/posts")
    public List<PostResponseDTO> getUserPosts(@PathVariable Long id) {
        return userService.getUserPosts(id);
    }

    @GetMapping("/usersWithPost")
    public List<UserWithPostsResponseDTO> getAllUsersWithPosts() {
        return userService.getAllUsersWithPosts();
    }

    @GetMapping("/userWithPost/{id}")
    public UserWithPostsResponseDTO getUserWithPostsById(@PathVariable Long id) {
        return userService.getUserWithPostsById(id);
    }
}
