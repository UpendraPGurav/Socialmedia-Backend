package com.socialmedia.controller;

import com.socialmedia.models.Story;
import com.socialmedia.models.User;
import com.socialmedia.services.StoryService;
import com.socialmedia.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StoryController {
    @Autowired
    private StoryService storyService;
    @Autowired
    private UserService userService;


    @PostMapping("/api/story")
    private Story createStory(@RequestBody Story story, @RequestHeader("Authorization") String jwt) {
        User reqUser = userService.findUserByJwtToken(jwt);
        Story createdStory = storyService.createStory(story, reqUser);
        return createdStory;
    }

    @GetMapping("/api/story/user/{userId}")
    private List<Story> findUsersStory(@PathVariable Integer userId,
                                 @RequestHeader("Authorization") String jwt) throws Exception {
        User reqUser = userService.findUserByJwtToken(jwt);
        List<Story> createdStory = storyService.findStoryByUser(userId);
        return createdStory;
    }
}
