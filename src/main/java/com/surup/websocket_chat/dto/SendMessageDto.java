package com.surup.websocket_chat.dto;

import com.surup.websocket_chat.constant.MessageType;

public record SendMessageDto(
        MessageType messageType,
        Long roomId,
        String userName,
        String content
) {
    public static SendMessageDto of(MessageType messageType, Long roomId, String userName, String content) {
        return new SendMessageDto(messageType, roomId, userName, content);
    }
}
