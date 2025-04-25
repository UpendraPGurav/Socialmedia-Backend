package com.socialmedia.services;

import com.socialmedia.models.User;

import java.util.List;

public interface UserService {
    public User registerUser(User user);
    public User findUserById(int id) throws Exception;
    public User findUserByEmail(String email);
    public User followUser(int user_id, int follower_id) throws Exception;
    public User updateUser(User user, Integer userId) throws Exception;
    public List<User> getAllUsers(String query);
    public List<User> searchUsers(String query);
    public User findUserByJwtToken(String jwt) ;

}
