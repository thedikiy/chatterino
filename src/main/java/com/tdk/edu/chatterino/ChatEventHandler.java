package com.tdk.edu.chatterino;


import com.tdk.edu.chatterino.entity.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class ChatEventHandler {


  @MessageMapping("/hello")
  @SendTo("/topic/greetings")
  public String greeting(Message message) throws Exception {
    Thread.sleep(1000); // simulated delay
    return HtmlUtils.htmlEscape(message.getContent());
  }

}