package com.socialmedia.services;

import com.socialmedia.models.Reels;
import com.socialmedia.models.User;

import java.util.List;

public interface ReelsService {
    public Reels createReel(Reels reel, User user);
    public List<Reels> findAllReels();
    public List<Reels> findUsersReels(Integer userId) throws Exception;
    public String deleteReel(Integer reelId,Integer userId) throws Exception;
    public Reels findReelById(Integer reelId) throws Exception;
}
