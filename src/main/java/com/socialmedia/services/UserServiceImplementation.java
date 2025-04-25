package com.socialmedia.services;

import com.socialmedia.config.JwtProvider;
import com.socialmedia.models.User;
import com.socialmedia.userRepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public User registerUser(User user) {
        User newUser = new User();
        newUser.setId(user.getId());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());

        User savedUser = userRepository.save(newUser);
        return savedUser;
    }

    @Override
    public User findUserById(int userId) throws Exception {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return user.get();
        }

        throw new Exception("User not found with user id " + userId);
    }

    @Override
    public User findUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return user;
    }

    @Override
    public User followUser(int regUserId, int userId2) throws Exception {
        User regUser = findUserById(regUserId);
        User user2 = findUserById(userId2);
        user2.getFollowers().add(regUser.getId());
        regUser.getFollowings().add(user2.getId());

        userRepository.save(regUser);
        userRepository.save(user2);
        return regUser;
    }

    @Override
    public User updateUser(User user, Integer userId) throws Exception {
        Optional<User> user1 = userRepository.findById(userId);
        if (user1.isEmpty()) {
            throw new Exception("user does't exist with id " + userId);
        }
        User oldUser = user1.get();

        if (user.getFirstName() != null) {
            oldUser.setFirstName(user.getFirstName());
        }
        if (user.getLastName() != null) {
            oldUser.setLastName(user.getLastName());
        }
        if (user.getEmail() != null) {
            oldUser.setEmail(user.getEmail());
        }
        if(user.getGender()!= null){
            oldUser.setGender(user.getGender());
        }
        return oldUser;
    }

    @Override
    public List<User> getAllUsers(String query) {
        return List.of();
    }

    public List<User> searchUsers(String query) {

        return userRepository.searchUser(query);

    }

    @Override
    public User findUserByJwtToken(String jwt) {
        String email= JwtProvider.getEmailFromJwtToken(jwt);

        User user = userRepository.findByEmail(email);
        return user;
    }

}
