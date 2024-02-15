package com.surup.websocket_chat.dto;

import com.surup.websocket_chat.domain.ChatRoom;

import java.time.LocalDateTime;

public record ChatRoomDto(
        Long id,
    String userName,
    String nickname,
    String title,
    String password,
    Integer count,
    LocalDateTime createdAt
) {
    public static ChatRoomDto of(Long id, String userName, String nickname, String title, String password, Integer count, LocalDateTime createdAt) {
        return new ChatRoomDto(id, userName, nickname, title, password, count, createdAt);
    }

    public static ChatRoomDto from(ChatRoom chatRoom) {
        return ChatRoomDto.of(
                chatRoom.getId(),
                chatRoom.getUserAccount().getUserName(),
                chatRoom.getUserAccount().getNickname(),
                chatRoom.getTitle(),
                chatRoom.getPassword(),
                chatRoom.getCount(),
                chatRoom.getCreatedAt()
        );
    }
}
