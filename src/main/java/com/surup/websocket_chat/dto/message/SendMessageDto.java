package com.surup.websocket_chat.dto.message;

import com.surup.websocket_chat.constant.MessageType;

public record SendMessageDto(
        MessageType messageType,
        Long id,
        String nickname,
        String content
) {
    public static SendMessageDto of(MessageType messageType, Long id, String nickname, String content) {
        return new SendMessageDto(messageType, id, nickname, content);
    }
}
