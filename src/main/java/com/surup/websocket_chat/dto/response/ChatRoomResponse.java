package com.surup.websocket_chat.dto.response;

import com.surup.websocket_chat.dto.ChatRoomDto;

import java.time.LocalDateTime;

public record ChatRoomResponse(
        Long id,
        String title,
        Boolean isEncrypted,
        String nickname,
        Integer count,
        LocalDateTime createdAt
) {
    public static ChatRoomResponse of(Long id, String title, Boolean isEncrypted, String nickname, Integer count, LocalDateTime createdAt) {
        return new ChatRoomResponse(id, title, isEncrypted, nickname, count, createdAt);
    }

    public static ChatRoomResponse from(ChatRoomDto chatRoomDto) {
        Boolean isEncrypted = false;
        if (!chatRoomDto.password().equals("")) {
            isEncrypted = true;
        }
        return ChatRoomResponse.of(
                chatRoomDto.id(),
                chatRoomDto.title(),
                isEncrypted,
                chatRoomDto.nickname(),
                chatRoomDto.count(),
                chatRoomDto.createdAt()
        );
    }
}
