package com.socialmedia.controller;

import com.socialmedia.models.Message;
import com.socialmedia.models.User;
import com.socialmedia.services.MessageService;
import com.socialmedia.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CreateMessageController {
    @Autowired
    private MessageService messageService;
    @Autowired
    private UserService userService;

    @PostMapping("/api/messages/chat/{chatId}")
    public Message createMessage(@RequestHeader("Authorization") String jwt,
                                 @RequestBody Message req,
                                 @PathVariable Integer chatId) throws Exception {

        User user = userService.findUserByJwtToken(jwt);
        Message message = messageService.createMessage(user, chatId, req);
        return message;

    }

    @GetMapping("/api/messages/chat/{chatId}")
    public List<Message> createMessage(@RequestHeader("Authorization") String jwt,
                                       @PathVariable Integer chatId) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        List<Message> messages = messageService.findChatsMessages(chatId);

        return messages;

    }
}
