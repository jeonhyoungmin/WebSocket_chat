package com.surup.websocket_chat.dto;

import com.surup.websocket_chat.constant.MessageType;

public record UserEnterDto(
        MessageType messageType,
        Long roomId,
        String userId
) {
    public static UserEnterDto of(MessageType messageType, Long roomId, String userId) {
        return new UserEnterDto(messageType, roomId, userId);
    }
}
