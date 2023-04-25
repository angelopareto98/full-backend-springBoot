package com.anghack.backfullcourse.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.anghack.backfullcourse.entity.Comment;
import com.anghack.backfullcourse.entity.Post;
import com.anghack.backfullcourse.exception.ResourceNotFoundException;
import com.anghack.backfullcourse.payload.CommentDto;
import com.anghack.backfullcourse.repository.CommentRepo;
import com.anghack.backfullcourse.repository.PostRepository;
import com.anghack.backfullcourse.service.CommentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepo commentRepo;
    private final PostRepository postRepo;
    private final ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer idPost) {
        Post postToComment = postRepo.findById(idPost)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "post id", idPost));

        Comment comment = this.modelMapper.map(commentDto, Comment.class);
        comment.setPost(postToComment);

        Comment commentSave = this.commentRepo.save(comment);

        return this.modelMapper.map(commentSave, CommentDto.class);
    }

    @Override
    public CommentDto updateComment(CommentDto commentDto, int idComment) {
        Comment comment = this.commentRepo.findById(idComment)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "comment id", idComment));

        comment.setContent(commentDto.getContent());

        Comment commentUpdated = this.commentRepo.save(comment);

        return this.modelMapper.map(commentUpdated, CommentDto.class);

    }

    @Override
    public List<CommentDto> getAllComments() {
        List<Comment> allComment = this.commentRepo.findAll();
        List<CommentDto> allCommentDtos = allComment.stream()
                .map(comment -> this.modelMapper.map(comment, CommentDto.class)).collect(Collectors.toList());

        return allCommentDtos;
    }

    @Override
    public CommentDto getCommentById(int idComment) {
        Comment commentFind = this.commentRepo.findById(idComment)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "comment id", idComment));
        CommentDto commentDtoFind = this.modelMapper.map(commentFind, CommentDto.class);

        return commentDtoFind;
    }

    @Override
    public void deleteComment(int idComment) {
        Comment commentFind = this.commentRepo.findById(idComment)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "comment id", idComment));

        this.commentRepo.delete(commentFind);
    }

}
