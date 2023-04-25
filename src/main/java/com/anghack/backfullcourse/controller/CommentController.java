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

import com.anghack.backfullcourse.payload.CommentDto;
import com.anghack.backfullcourse.service.CommentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api")
public class CommentController {

    private final CommentService commentService;

    @PostMapping(path = "/comment/post/{postId}")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto,
            @PathVariable("postId") int postId) {

        CommentDto commentCreated = this.commentService.createComment(commentDto, postId);

        return new ResponseEntity<>(commentCreated, HttpStatus.CREATED);
    }

    @GetMapping(path = "/comment/all")
    public ResponseEntity<List<CommentDto>> getAllComments() {
        List<CommentDto> allComments = this.commentService.getAllComments();

        return new ResponseEntity<List<CommentDto>>(allComments, HttpStatus.OK);
    }

}
