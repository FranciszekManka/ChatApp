package com.JavaChatApp.ChatApp.config;


import com.JavaChatApp.ChatApp.chat.MessageType;
import com.JavaChatApp.ChatApp.chat.messageHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@RequiredArgsConstructor
@Slf4j


public class WebsocketEventListener {


    public final SimpMessageSendingOperations Template;



    @EventListener
    public void handleWebSocketDisconnect(
        SessionDisconnectEvent event
    ){
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String username = (String) headerAccessor.getSessionAttributes().get("username");

        if(username != null){
            log.info("user Disconnected: {}", username);
            var message = messageHandler.builder().type(MessageType.LEAVE).sender("username").build();
            Template.convertAndSend("/topic/public", message);
        }
    }

}
