package com.anghack.backfullcourse.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.anghack.backfullcourse.entity.Category;
import com.anghack.backfullcourse.entity.Post;
import com.anghack.backfullcourse.entity.User;
import com.anghack.backfullcourse.exception.ResourceNotFoundException;
import com.anghack.backfullcourse.payload.PostDto;
import com.anghack.backfullcourse.payload.PostResponse;
import com.anghack.backfullcourse.repository.CategoryRepo;
import com.anghack.backfullcourse.repository.PostRepository;
import com.anghack.backfullcourse.repository.UserRepo;
import com.anghack.backfullcourse.service.PostService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostServiceImpl implements PostService {

        private final PostRepository postRepository;
        private final UserRepo userRepo;
        private final CategoryRepo categoryRepo;
        private final ModelMapper modelMapper;

        @Override
        public PostDto createPost(PostDto postDto, int userId, Integer categoryId) {

                User user = this.userRepo.findById(userId)
                                .orElseThrow(() -> new ResourceNotFoundException("User", "user id", userId));

                // log.info(user + "user **************");
                Category category = this.categoryRepo.findById(categoryId)
                                .orElseThrow(() -> new ResourceNotFoundException("Category", "category Id",
                                                categoryId));

                // log.info(category + "category **********************");
                Post post = this.modelMapper.map(postDto, Post.class);

                post.setImageName("image123.png");
                post.setAddedDate(new Date());
                post.setUser(user);
                post.setCategory(category);

                Post newPost = this.postRepository.save(post);

                PostDto postDtoAdded = this.modelMapper.map(newPost, PostDto.class);

                return postDtoAdded;

        }

        @Override
        public PostDto updatePost(PostDto postDto, Integer postId) {
                Post postSelected = this.postRepository.findById(postId)
                                .orElseThrow(() -> new ResourceNotFoundException("Post", "post ID", postId));
                postSelected.setContent(postDto.getContent());
                postSelected.setTitle(postDto.getTitle());
                postSelected.setImageName(postDto.getImageName());

                Post updatedPost = this.postRepository.save(postSelected);

                return this.modelMapper.map(updatedPost, PostDto.class);
        }

        @Override
        public void deletePost(Integer postId) {
                Post postSelected = this.postRepository.findById(postId)
                                .orElseThrow(() -> new ResourceNotFoundException("Post", "post Id", postId));

                this.postRepository.delete(postSelected);
        }

        @Override
        public PostResponse getAllPost(Integer pageNumber, Integer pageSize) {

                Pageable p = PageRequest.of(pageNumber, pageSize);

                Page<Post> pagePost = this.postRepository.findAll(p);

                List<Post> allPosts = pagePost.getContent();

                List<PostDto> listPostDtos = allPosts.stream().map(post -> this.modelMapper.map(post, PostDto.class))
                                .collect(Collectors.toList());

                PostResponse postResponse = new PostResponse();
                postResponse.setContent(listPostDtos);
                postResponse.setPageNumber(pagePost.getNumber());
                postResponse.setPageSize(pagePost.getSize());
                postResponse.setTotalElements(pagePost.getTotalElements());
                postResponse.setTotalPages(pagePost.getTotalPages());
                postResponse.setLastPage(pagePost.isLast());

                return postResponse;
        }

        @Override
        public PostDto getPostById(Integer postId) {
                Post post = this.postRepository.findById(postId)
                                .orElseThrow(() -> new ResourceNotFoundException("Post", "post Id", postId));

                return this.modelMapper.map(post, PostDto.class);
        }

        @Override
        public List<PostDto> getPostByCategory(Integer categoryId) {
                Category category = this.categoryRepo.findById(categoryId)
                                .orElseThrow(() -> new ResourceNotFoundException("Category", "category Id",
                                                categoryId));
                List<Post> posts = this.postRepository.findByCategory(category);

                List<PostDto> listPostDtos = posts.stream().map(post -> this.modelMapper.map(post, PostDto.class))
                                .collect(Collectors.toList());

                return listPostDtos;
        }

        @Override
        public List<PostDto> getPostByUser(int userId) {
                User user = this.userRepo.findById(userId)
                                .orElseThrow(() -> new ResourceNotFoundException("User", "user Id", userId));
                List<Post> posts = this.postRepository.findByUser(user);

                List<PostDto> listPosts = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
                                .collect(Collectors.toList());

                return listPosts;
        }

        @Override
        public List<Post> searchPosts(String keyword) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'searchPosts'");
        }

}
