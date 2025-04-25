package com.socialmedia.controller;

import com.socialmedia.models.User;
import com.socialmedia.services.UserService;
import com.socialmedia.userRepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

  /*
   @PostMapping("/users")
    public User createUser(@RequestBody User user) {

        User savedUser = userService.registerUser(user);
        return savedUser;
    }

   */

 /*   @GetMapping("/api/users")
    public List<User> getUsers() {
        List<User> users = new ArrayList<User>();
        User user = new User(1, "Upendra", "gurav", "upendra@gmail", "1234");
        users.add(user);
        return users;
        List<User> users = userRepository.findAll();
        return users;
    }

      @GetMapping("/api/users/{userId}")
    public User getUserById(@PathVariable("userId") int id) throws Exception {
        User user = new User(1, "Upendra", "gurav", "upendra@gmail", "1234");
        user.setId(id);
        return user;
        optional is used whether there will be user or no
        User user = userService.findUserById(id);
        return user;
    }

     @PutMapping("/api/users/{userId}")
    public User updateUser(@RequestBody User user,@PathVariable Integer userId) throws Exception {//when user id is dyanamic
       User user1 = new User(1, "Upendra", "gurav", "upendra@gmail", "1234");
        if (user.getFirstName() != null) {
            user1.setFirstName(user.getFirstName());
        }
        if (user.getLastName() != null) {
            user1.setLastName(user.getLastName());
        }
        if (user.getEmail() != null) {
            user1.setEmail(user.getEmail());
        }
        return user1;

        User userUpdated = userService.updateUser(user);
        return userUpdated;
    }


     @DeleteMapping("/users/{userId}")
   public String deleteUser(@PathVariable("userId") int id) throws Exception {
       Optional<User> user = userRepository.findById(id);

       if(user.isEmpty()){
           throw new Exception("User doesn't exist with id "+ id);
       }
       userRepository.delete(user.get());

       return "user deleted successfully " + id;
   }


      @PutMapping("/api/users/follow/{userId1}/{userId2}")
    public User followUserHandler(@PathVariable Integer userId1, @PathVariable Integer userId2) throws Exception {
        User user = userService.followUser(userId1, userId2);
        return user;
    }


  */


    @GetMapping("/api/users")
    public List<User> getUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }


    @GetMapping("/api/users/{userId}")
    public User getUserById(@PathVariable("userId") int id) throws Exception {
        User user = userService.findUserById(id);
        return user;
    }


    @PutMapping("/api/users")
    public User updateUser(@RequestHeader("Authorization") String jwt, @RequestBody User user) throws Exception {
        User regUser = userService.findUserByJwtToken(jwt);

        User userUpdated = userService.updateUser(user, regUser.getId());
        return userUpdated;
    }


    @PutMapping("/api/users/follow/{userId2}")
    public User followUserHandler(@RequestHeader("Authorization") String jwt, @PathVariable Integer userId2) throws Exception {
        User regUser = userService.findUserByJwtToken(jwt);

        User user = userService.followUser(regUser.getId(), userId2);
        return user;
    }

    @GetMapping("/api/users/search")
    public List<User> searchUsers(@RequestParam("query") String query) {
        List<User> users = userService.searchUsers(query);
        return users;
    }

    @GetMapping("/api/users/profile")
    public User getUserFromUserToken(@RequestHeader("Authorization") String jwt) {
        User user = userService.findUserByJwtToken(jwt);
        user.setPassword(null);
        return user;
    }
}

