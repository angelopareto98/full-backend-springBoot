package com.anghack.backfullcourse.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

}
