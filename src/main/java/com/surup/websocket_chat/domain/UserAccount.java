package com.surup.websocket_chat.domain;

import java.time.LocalDateTime;

public class UserAccount {
    private Long userId; // 유저 id
    private String email; // 이메일
    private String nickname; // 닉네임
    private String memo; // 메모

    private LocalDateTime createdAt; // 생성 일시
    private String createdBy; // 생성자
    private LocalDateTime modifiedAt; // 수정 일시
    private String modifiedBy; // 수정자
}
