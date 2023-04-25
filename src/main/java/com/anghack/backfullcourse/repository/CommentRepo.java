package com.anghack.backfullcourse.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anghack.backfullcourse.entity.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

}
