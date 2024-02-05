package com.surup.websocket_chat.dto;

import com.surup.websocket_chat.constant.MessageType;

public record SendMessageDto(
        MessageType messageType,
        Long roomId,
        String userId,
        String content
) {
    public static SendMessageDto of(MessageType messageType, Long roomId, String userId, String content) {
        return new SendMessageDto(messageType, roomId, userId, content);
    }
}
