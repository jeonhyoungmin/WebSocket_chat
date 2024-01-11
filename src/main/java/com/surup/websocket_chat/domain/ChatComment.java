package com.surup.websocket_chat.domain;

import java.time.LocalDateTime;

public class ChatComment {
    private Long id; // 채팅 id
    private ChatRoom chatRoom; // 채팅방 (id)
    private UserAccount userAccount; // 유저 계정 (id)
    private String content; // 본문
    private String parentComment; // 부모 채팅

    private LocalDateTime createdAt; // 생성 일시
    private String createdBy; // 생성자
    private LocalDateTime modifiedAt; // 생성 일시
    private String modifiedBy; // 수정자
}
