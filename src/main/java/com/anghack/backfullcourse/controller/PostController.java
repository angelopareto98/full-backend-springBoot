package com.anghack.backfullcourse.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import org.springframework.web.multipart.MultipartFile;

import com.anghack.backfullcourse.config.AppConstants;
import com.anghack.backfullcourse.payload.ApiResponse;
import com.anghack.backfullcourse.payload.PostDto;
import com.anghack.backfullcourse.payload.PostResponse;
import com.anghack.backfullcourse.service.FileService;
import com.anghack.backfullcourse.service.PostService;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final FileService fileService;
    @Value("${project.image}")
    private String path;

    @PostMapping(path = "/user/{userId}/category/{categoryId}/post")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable("userId") int idUser,
            @PathVariable Integer categoryId) {
        PostDto createPost = this.postService.createPost(postDto, idUser, categoryId);

        return new ResponseEntity<>(createPost, HttpStatus.CREATED);
    }

    @GetMapping(path = "/all/post")
    public ResponseEntity<PostResponse> getAllPost(
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir) {

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

        return new ResponseEntity<>(new ApiResponse("Post deleted successfuly", true), HttpStatus.OK);
    }

    @GetMapping(path = "/search/post/{keyTitle}")
    public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable(name = "keyTitle") String keyTitle) {
        List<PostDto> resultSearchBytitle = this.postService.searchPostsByTitle(keyTitle);

        return new ResponseEntity<>(resultSearchBytitle, HttpStatus.OK);

    }

    // To upload image
    @PostMapping(path = "/post/image/upload/{postId}")
    public ResponseEntity<PostDto> uploadPostImage(
            @RequestParam("image") MultipartFile image,
            @PathVariable Integer postId) throws IOException {
        String fileName = this.fileService.uploadFile(path, image);

        PostDto postDto = this.postService.getPostById(postId);
        postDto.setImageName(fileName);
        PostDto updatePost = this.postService.updatePost(postDto, postId);

        return new ResponseEntity<>(updatePost, HttpStatus.OK);
    }

    // method to serve files
    @GetMapping(path = "/post/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(@PathVariable("imageName") String imageName, HttpServletResponse response)
            throws IOException {

        InputStream resourcee = this.fileService.getResource(path, imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        org.springframework.util.StreamUtils.copy(resourcee, response.getOutputStream());
    }

}
