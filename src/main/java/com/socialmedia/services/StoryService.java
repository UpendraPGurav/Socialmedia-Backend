package com.socialmedia.services;

import com.socialmedia.models.Story;
import com.socialmedia.models.User;

import java.util.List;

public interface StoryService {
    public Story createStory(Story story, User user);
    public List<Story> findStoryByUser(Integer userId) throws Exception;
}
