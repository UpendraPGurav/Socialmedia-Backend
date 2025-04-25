package com.socialmedia.services;

import com.socialmedia.models.Story;
import com.socialmedia.models.User;
import com.socialmedia.userRepository.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StoryServiceImplementation implements StoryService {

    @Autowired
    private StoryRepository storyRepository;
    @Autowired
    private  UserService userService;

    @Override
    public Story createStory(Story story, User user) {
        Story createdStory = new Story();

        createdStory.setCaption(story.getCaption());
        createdStory.setImage(story.getImage());
        createdStory.setUser(user);
        createdStory.setTimeStamp(LocalDateTime.now());
        return storyRepository.save(createdStory);
    }

    @Override
    public List<Story> findStoryByUser(Integer userId) throws Exception {
        User user = userService.findUserById(userId);

        return storyRepository.findByUserId(userId);
    }
}
