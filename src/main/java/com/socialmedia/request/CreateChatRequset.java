package com.socialmedia.request;

import com.socialmedia.models.User;
import com.socialmedia.services.ChatService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Data
public class CreateChatRequset {

    private Integer userId;
}
