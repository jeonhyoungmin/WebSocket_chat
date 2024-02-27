package com.surup.websocket_chat.controller;

import com.surup.websocket_chat.dto.message.SendMessageDto;
import com.surup.websocket_chat.dto.message.UserEnterDto;
import com.surup.websocket_chat.dto.message.UserLeaveDto;
import com.surup.websocket_chat.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class WebSocketController {

    private final SimpMessagingTemplate template;
    private final ChatRoomService chatRoomService;

    @MessageMapping("/userEnter")
    public void enterUser(@Payload UserEnterDto userEnterDto) {
        chatRoomService.increaseCount(userEnterDto.id());
        template.convertAndSend("/sub/room/" + userEnterDto.id(), userEnterDto);
    }

    @MessageMapping("/sendMessage")
    public void sendMessage(@Payload SendMessageDto sendMessageDto) {
        template.convertAndSend("/sub/room/" + sendMessageDto.id(), sendMessageDto);
    }

    @MessageMapping("/leaveMessage")
    public void leaveUser(@Payload UserLeaveDto userLeaveDto) {
        chatRoomService.decreaseCount(userLeaveDto.id(), userLeaveDto.nickname());
        template.convertAndSend("/sub/room/" + userLeaveDto.id(), userLeaveDto);
    }

}
