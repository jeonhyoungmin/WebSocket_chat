package com.surup.websocket_chat.dto;

import java.time.LocalDateTime;

public record ChatRoomResponse(
        String nickname,
        String title,
        Integer count,
        LocalDateTime createdAt
) {
    public static ChatRoomResponse of(String nickname, String title, Integer count, LocalDateTime createdAt) {
        return new ChatRoomResponse(nickname, title, count, createdAt);
    }

    public static ChatRoomResponse from(ChatRoomDto chatRoomDto) {
        return ChatRoomResponse.of(
                chatRoomDto.nickname(),
                chatRoomDto.title(),
                chatRoomDto.count(),
                chatRoomDto.createdAt()
        );
    }
}
