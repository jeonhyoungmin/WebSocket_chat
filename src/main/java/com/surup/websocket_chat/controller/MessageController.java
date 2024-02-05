package com.surup.websocket_chat.controller;

import com.surup.websocket_chat.dto.SendMessageDto;
import com.surup.websocket_chat.dto.UserEnterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class MessageController {

    private final SimpMessagingTemplate template;

    @MessageMapping("/userEnter")
    public void enterUser(@Payload UserEnterDto userEnterDto) {
        template.convertAndSend("/sub/room", userEnterDto);
    }

    @MessageMapping("/sendMessage")
    public void sendMessage(@Payload SendMessageDto sendMessageDto) {
        template.convertAndSend("/sub/room", sendMessageDto);
    }
}
