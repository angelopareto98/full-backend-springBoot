package com.anghack.backfullcourse.service;

import java.util.List;

import com.anghack.backfullcourse.payload.CommentDto;

public interface CommentService {
    CommentDto createComment(CommentDto commentDto, Integer idPost);

    CommentDto updateComment(CommentDto commentDto, int idComment);

    List<CommentDto> getAllComments();

    CommentDto getCommentById(int idComment);

    void deleteComment(int idComment);
}
