package com.surup.websocket_chat.dto;

import java.time.LocalDateTime;

public record ChatRoomResponse(
        Long id,
        String title,
        String nickname,
        Integer count,
        LocalDateTime createdAt
) {
    public static ChatRoomResponse of(Long id, String title, String nickname, Integer count, LocalDateTime createdAt) {
        return new ChatRoomResponse(id, title, nickname, count, createdAt);
    }

    public static ChatRoomResponse from(ChatRoomDto chatRoomDto) {
        return ChatRoomResponse.of(
                chatRoomDto.id(),
                chatRoomDto.title(),
                chatRoomDto.nickname(),
                chatRoomDto.count(),
                chatRoomDto.createdAt()
        );
    }
}
