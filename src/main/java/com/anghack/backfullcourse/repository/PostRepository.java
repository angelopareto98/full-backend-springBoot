package com.anghack.backfullcourse.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anghack.backfullcourse.entity.Category;
import com.anghack.backfullcourse.entity.Post;
import com.anghack.backfullcourse.entity.User;
import com.anghack.backfullcourse.payload.PostDto;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {

    List<PostDto> findByUser(User user);

    List<PostDto> findByCategory(Category category);

}
