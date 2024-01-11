package com.surup.websocket_chat.domain;

import java.time.LocalDateTime;

public class ChatRoom {
    private Long id; // 채팅방 id
    private UserAccount userAccount; // 유저 계정 (id)
    private String password; // 채팅방 암호
    private String title; // 채팅방 제목

    private LocalDateTime createdAt; // 생성 일시
    private String createdBy; // 생성자
    private LocalDateTime modifiedAt; // 수정 일시
    private String modifiedBy; // 수정자
}
