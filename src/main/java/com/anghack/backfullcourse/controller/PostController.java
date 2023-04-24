package com.anghack.backfullcourse.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anghack.backfullcourse.payload.ApiResponse;
import com.anghack.backfullcourse.payload.PostDto;
import com.anghack.backfullcourse.payload.PostResponse;
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
    public ResponseEntity<PostResponse> getAllPost(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = "postId", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {

        PostResponse postsResponse = this.postService.getAllPost(pageNumber, pageSize, sortBy, sortDir);

        return new ResponseEntity<>(postsResponse, HttpStatus.OK);
    }

    @GetMapping(path = "/post/{categoryId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer categoryId) {
        PostDto postById = this.postService.getPostById(categoryId);

        return ResponseEntity.ok().body(postById);
    }

    @PutMapping(path = "/post/{categoryId}/update")
    public ResponseEntity<PostDto> updatePostById(@RequestBody PostDto postDto, @PathVariable Integer categoryId) {
        this.postService.updatePost(postDto, categoryId);

        return new ResponseEntity<>(postDto, HttpStatus.OK);
    }

    @GetMapping(path = "/user/{userId}/post")
    public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable int userId) {
        List<PostDto> listPosts = this.postService.getPostByUser(userId);

        return new ResponseEntity<>(listPosts, HttpStatus.OK);
    }

    @GetMapping(path = "/category/{postId}/post")
    public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer postId) {
        List<PostDto> listPosts = this.postService.getPostByCategory(postId);

        return new ResponseEntity<>(listPosts, HttpStatus.OK);
    }

    @DeleteMapping(path = "/post/{postId}/delete")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId) {
        this.postService.deletePost(postId);

        return new ResponseEntity<ApiResponse>(new ApiResponse("Post deleted successfuly", true), HttpStatus.OK);
    }

    @GetMapping(path = "/search/post/{keyTitle}")
    public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable(name = "keyTitle") String keyTitle) {
        List<PostDto> resultSearchBytitle = this.postService.searchPostsByTitle(keyTitle);

        return new ResponseEntity<>(resultSearchBytitle, HttpStatus.OK);

    }

}
