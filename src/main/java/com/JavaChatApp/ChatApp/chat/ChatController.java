package com.JavaChatApp.ChatApp.chat;


import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @MessageMapping("/chat.SendMessage")
    @SendTo("/topic/public")
    public messageHandler SendMessage(
            @Payload messageHandler message
    ) {
        return message;
    }
    @MessageMapping("/chat.SendMessage")
    @SendTo("/topic/public")
    public messageHandler addUser(
            @Payload messageHandler message,
            SimpMessageHeaderAccessor headerAccessor
    ){
        // handling username in websocket sess
        headerAccessor.getSessionAttributes().put("username", message.getSender());
        return message;
    }



}
