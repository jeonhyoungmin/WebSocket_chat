package com.surup.websocket_chat.dto.message;

import com.surup.websocket_chat.constant.MessageType;

public record UserLeaveDto(
        MessageType messageType,
        Long id,
        String nickname
) {
    public static UserLeaveDto of(MessageType messageType, Long id, String nickname) {
        return new UserLeaveDto(messageType, id, nickname);
    }
}
