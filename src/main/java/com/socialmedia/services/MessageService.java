package com.socialmedia.services;

import com.socialmedia.models.Chat;
import com.socialmedia.models.Message;
import com.socialmedia.models.User;

import java.util.List;

public interface MessageService {
    public Message createMessage(User user, Integer chatId, Message req) throws Exception;

    public List<Message> findChatsMessages(Integer chatId) throws Exception;
}
