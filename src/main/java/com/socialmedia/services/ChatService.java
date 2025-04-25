package com.socialmedia.services;

import com.socialmedia.models.Chat;
import com.socialmedia.models.User;

import java.util.List;

public interface ChatService {
    public Chat CreateChat(User reqUser, User user2);

    public Chat findChatById(Integer chatId) throws Exception;

    public List<Chat> findUsersChat(Integer userId);
}
