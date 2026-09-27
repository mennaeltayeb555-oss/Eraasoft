package org.example.task11.service;


import org.example.task11.dto.postdto.PostRequestDTO;
import org.example.task11.dto.postdto.PostResponseDTO;
import org.example.task11.dto.postdto.PostWithUserResponseDTO;

import java.util.List;

public interface PostService {

    PostResponseDTO createPost(PostRequestDTO dto);

    PostResponseDTO updatePost(Long id, PostRequestDTO dto);

    void deletePost(Long id);

    List<PostResponseDTO> getAllPosts();

    PostResponseDTO getPostById(Long id);

    List<PostWithUserResponseDTO> getAllPostsWithUsers();

    PostWithUserResponseDTO getPostWithUserById(Long id);
}