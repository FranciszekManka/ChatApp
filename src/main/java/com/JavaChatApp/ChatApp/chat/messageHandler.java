package com.JavaChatApp.ChatApp.chat;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder


public class messageHandler {

    private String message_Content;
    private String sender;

    private MessageType type;

}
