package com.anghack.backfullcourse.service;

import java.util.List;

import com.anghack.backfullcourse.entity.Post;
import com.anghack.backfullcourse.payload.PostDto;
import com.anghack.backfullcourse.payload.PostResponse;

public interface PostService {
    PostDto createPost(PostDto postDto, int userId, Integer categoryId);

    PostDto updatePost(PostDto postDto, Integer postId);

    void deletePost(Integer postId);

    PostResponse getAllPost(Integer pageNumber, Integer pageSize);

    PostDto getPostById(Integer postId);

    List<PostDto> getPostByCategory(Integer categoryId);

    List<PostDto> getPostByUser(int userId);

    List<Post> searchPosts(String keyword);
}
