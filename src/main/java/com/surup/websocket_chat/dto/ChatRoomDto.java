package com.surup.websocket_chat.dto;

import com.surup.websocket_chat.domain.ChatRoom;

import java.time.LocalDateTime;

public record ChatRoomDto(
    String userId,
    String nickname,
    String title,
    String password,
    Integer count,
    LocalDateTime createdAt
) {
    public static ChatRoomDto of(String userId, String nickname, String title, String password, Integer count, LocalDateTime createdAt) {
        return new ChatRoomDto(userId, nickname, title, password, count, createdAt);
    }

    public static ChatRoomDto from(ChatRoom chatRoom) {
        return ChatRoomDto.of(
                chatRoom.getUserAccount().getUserId(),
                chatRoom.getNickname(),
                chatRoom.getTitle(),
                chatRoom.getPassword(),
                chatRoom.getCount(),
                chatRoom.getCreatedAt()
        );
    }
}
