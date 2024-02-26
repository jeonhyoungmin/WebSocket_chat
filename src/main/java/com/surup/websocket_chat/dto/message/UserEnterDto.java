package com.surup.websocket_chat.dto.message;

import com.surup.websocket_chat.constant.MessageType;

public record UserEnterDto(
        MessageType messageType,
        Long id,
        String nickname
) {
    public static UserEnterDto of(MessageType messageType, Long id, String nickname) {
        return new UserEnterDto(messageType, id, nickname);
    }
}
