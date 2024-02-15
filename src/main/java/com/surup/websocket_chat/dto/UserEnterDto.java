package com.surup.websocket_chat.dto;

import com.surup.websocket_chat.constant.MessageType;

public record UserEnterDto(
        MessageType messageType,
        Long roomId,
        String userName
) {
    public static UserEnterDto of(MessageType messageType, Long roomId, String userName) {
        return new UserEnterDto(messageType, roomId, userName);
    }
}
