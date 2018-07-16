package com.tdk.edu.chatterino;


import com.tdk.edu.chatterino.entity.Message;
import com.tdk.edu.chatterino.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import java.util.Map;

@Controller
public class ChatEventHandler {

    private MessageRepository messageRepository;

    @Autowired
    public ChatEventHandler(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public String greeting(Message message, OAuth2Authentication principal) throws Exception {
        message.setUsername(((Map<String, String>) principal.getUserAuthentication().getDetails()).get("displayName"));
        return HtmlUtils.htmlEscape(messageRepository.save(message).getContent());
    }

}