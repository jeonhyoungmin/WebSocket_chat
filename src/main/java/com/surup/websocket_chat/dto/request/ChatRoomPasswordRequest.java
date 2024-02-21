package com.surup.websocket_chat.dto.request;

public record ChatRoomPasswordRequest(
        Long id,
        String password
) {
    public static ChatRoomPasswordRequest of(Long id, String password) {
        return new ChatRoomPasswordRequest(id, password);
    }
}
