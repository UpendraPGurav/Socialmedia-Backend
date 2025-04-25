package com.socialmedia.controller;

import com.socialmedia.models.Chat;
import com.socialmedia.models.User;
import com.socialmedia.request.CreateChatRequset;
import com.socialmedia.services.ChatService;
import com.socialmedia.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ChatController {
    @Autowired
    private ChatService chatService;
    @Autowired
    private UserService userService;

    @PostMapping("/api/chats")
    public Chat createChat(@RequestHeader("Authorization") String jwt,
                           @RequestBody CreateChatRequset req) throws Exception {
        User reqUser = userService.findUserByJwtToken(jwt);
        User user2 = userService.findUserById(req.getUserId());
        Chat chat = chatService.CreateChat(reqUser, user2);
        return chat;
    }

    @GetMapping("/api/chats")
    public List<Chat> findUsersChat(@RequestHeader("Authorization") String jwt) {
        User user = userService.findUserByJwtToken(jwt);
        List<Chat> chats = chatService.findUsersChat(user.getId());
        return chats;
    }
}
