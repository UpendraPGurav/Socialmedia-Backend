package com.socialmedia.controller;

import com.socialmedia.models.Reels;
import com.socialmedia.models.User;
import com.socialmedia.response.ApiResponse;
import com.socialmedia.services.ReelsService;
import com.socialmedia.services.UserService;
import com.socialmedia.userRepository.ReelsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReelsController {
    @Autowired
    private ReelsService reelsService;
    @Autowired
    UserService userService;
    @Autowired
    private ReelsRepository reelsRepository;

    @PostMapping("/api/reels")
    public Reels createReels(@RequestBody Reels reels,
                             @RequestHeader("Authorization") String jwt){
        User reqUser = userService.findUserByJwtToken(jwt);
        Reels createdReels = reelsService.createReel(reels,reqUser);
//        reelsRepository.save(createdReels);
        return createdReels;
    }

    @GetMapping("/api/reels")
    public List<Reels> findAllReels(){
        List<Reels> allReels = reelsService.findAllReels();
        return allReels;
    }

    @GetMapping("/api/reels/user/{userId}")
    public List<Reels> findUsersReels(@PathVariable Integer userId) throws Exception {
        List<Reels> reels = reelsService.findUsersReels(userId);
        return reels;
    }

    @DeleteMapping("/api/reels/{reelId}")
    public ResponseEntity<ApiResponse> deleteReel(@RequestHeader("Authorization") String jwt,@PathVariable Integer reelId) throws Exception {
        User reqUser = userService.findUserByJwtToken(jwt);
        String message = reelsService.deleteReel(reelId,reqUser.getId());
        ApiResponse res = new ApiResponse(message,true);
        return ResponseEntity.ok(res);
    }
}
