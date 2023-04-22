package com.anghack.backfullcourse.service;

import java.util.List;

import com.anghack.backfullcourse.entity.Post;
import com.anghack.backfullcourse.payload.PostDto;

public interface PostService {
    PostDto createPost(PostDto postDto, int userId, Integer categoryId);

    Post updatePost(PostDto postDto, Integer postId);

    void deletePost(Integer postId);

    List<Post> getAllPost();

    Post getPostById(Integer postId);

    List<Post> getPostByCategory(Integer categoryId);

    List<Post> getPostByUser(Integer userId);

    List<Post> searchPosts(String keyword);
}
