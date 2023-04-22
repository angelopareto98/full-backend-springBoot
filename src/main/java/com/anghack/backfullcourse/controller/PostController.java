package com.anghack.backfullcourse.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anghack.backfullcourse.payload.PostDto;
import com.anghack.backfullcourse.service.PostService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping(path = "/user/{userId}/category/{categoryId}/post")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable("userId") int idUser,
            @PathVariable Integer categoryId) {
        PostDto createPost = this.postService.createPost(postDto, idUser, categoryId);

        return new ResponseEntity<>(createPost, HttpStatus.CREATED);
    }

    @GetMapping(path = "/all/post")
    public ResponseEntity<List<PostDto>> getAllPost() {
        return new ResponseEntity<>(this.postService.getAllPost(), HttpStatus.OK);
    }

    @GetMapping(path = "/user/{userId}/post")
    public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable int userId) {
        List<PostDto> listPosts = this.postService.getPostByUser(userId);

        return new ResponseEntity<>(listPosts, HttpStatus.OK);
    }

    @GetMapping(path = "/category/{categoryId}/post")
    public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId) {
        List<PostDto> listPosts = this.postService.getPostByCategory(categoryId);

        return new ResponseEntity<>(listPosts, HttpStatus.OK);
    }

}
