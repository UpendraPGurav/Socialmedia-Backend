package com.socialmedia.controller;

import com.socialmedia.models.Post;
import com.socialmedia.models.User;
import com.socialmedia.response.ApiResponse;
import com.socialmedia.services.PostService;
import com.socialmedia.services.UserService;
import com.socialmedia.userRepository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    PostRepository postRepository;

    @Autowired
    PostService postService;

    @Autowired
    UserService userService;

//    @PostMapping("/posts/user/{userId}")
//    public ResponseEntity<Post> createPost(@RequestBody Post post, @PathVariable Integer userId) throws Exception {
//        Post createdPost = postService.createNewPost(post, userId);
//        return new ResponseEntity<>(createdPost, HttpStatus.ACCEPTED);
//    }

    /*

    @PostMapping("/posts/user/{userId}")
    public ResponseEntity<Post> createPost(@RequestBody Post post,@PathVariable Integer userId) throws Exception {
        Post createdPost = postService.createNewPost(post,userId);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    */

    @PostMapping("/api/posts")
    public ResponseEntity<Post> createPost(@RequestHeader("Authorization") String jwt,
                                           @RequestBody Post post) throws Exception {
        User regUser = userService.findUserByJwtToken(jwt);
        Post createdPost = postService.createNewPost(post, regUser.getId());
        postRepository.save(createdPost);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@RequestHeader("Authorization") String jwt,
                                                  @PathVariable Integer postId
    ) throws Exception {
        User regUser = userService.findUserByJwtToken(jwt);
        String message = postService.deletePost(postId, regUser.getId());
        ApiResponse res = new ApiResponse(message, true);
        return new ResponseEntity<ApiResponse>(res, HttpStatus.OK);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<Post> findPostByIdHandler(@PathVariable Integer postId) throws Exception {
        Post post = postService.findPostById(postId);
        return new ResponseEntity<Post>(post, HttpStatus.ACCEPTED);
    }

    @GetMapping("/posts/user/{userId}")
    public ResponseEntity<List<Post>> findUsersPost(@PathVariable Integer userId) throws Exception {
        List<Post> posts = postService.findPostByUserId(userId);
        return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
    }


    @GetMapping("/posts")
    public ResponseEntity<List<Post>> findAllPosts() {
        List<Post> posts = postService.findAllPost();
        return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
    }

    @PutMapping("/posts/save/{postId}")
    public ResponseEntity<Post> savedPostHandler(@RequestHeader("Authorization") String jwt,
                                                 @PathVariable Integer postId) throws Exception {
        User reqUser = userService.findUserByJwtToken(jwt);
        Post post = postService.savedPost(postId, reqUser.getId());
        return new ResponseEntity<Post>(post, HttpStatus.ACCEPTED);
    }

    @PutMapping("/posts/like/{postId}")
    public ResponseEntity<Post> likePostHandler(@PathVariable Integer postId,
                                                @RequestHeader("Authorization") String jwt) throws Exception {
        User reqUser = userService.findUserByJwtToken(jwt);
        Post post = postService.likePost(postId, reqUser.getId());
        return new ResponseEntity<Post>(post, HttpStatus.ACCEPTED);
    }
}
