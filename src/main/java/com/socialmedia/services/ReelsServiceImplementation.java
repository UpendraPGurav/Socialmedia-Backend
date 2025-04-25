package com.socialmedia.services;

import com.socialmedia.models.Reels;
import com.socialmedia.models.User;
import com.socialmedia.userRepository.ReelsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReelsServiceImplementation implements ReelsService{
    @Autowired
    private ReelsRepository reelsRepository;

    @Autowired
    private UserService userService;

    @Override
    public Reels createReel(Reels reel, User user) {
        Reels createReel = new Reels();

        createReel.setTitle(reel.getTitle());
        createReel.setUser(user);
        createReel.setVideo(reel.getVideo());

        return reelsRepository.save(createReel);
    }

    @Override
    public List<Reels> findAllReels() {

        return reelsRepository.findAll();
    }

    @Override
    public List<Reels> findUsersReels(Integer userId) throws Exception {
        userService.findUserById(userId);
        return reelsRepository.findByUserId(userId);
    }

    @Override
    public String deleteReel(Integer reelId, Integer userId) throws Exception {
        Reels reel = findReelById(reelId);
        User user = userService.findUserById(userId);
        if(reel.getUser().getId()!= user.getId()){
            throw  new Exception(("You are not allowed to delete this Reel"));
        }
        reelsRepository.delete(reel);
        return "Reel Deleted Successfully";
    }

    @Override
    public Reels findReelById(Integer reelId) throws Exception {
        Optional<Reels> opt = reelsRepository.findById(reelId);
        if(opt.isEmpty()){
            throw new Exception("Reel not found with id");
        }
        return opt.get();
    }


}
