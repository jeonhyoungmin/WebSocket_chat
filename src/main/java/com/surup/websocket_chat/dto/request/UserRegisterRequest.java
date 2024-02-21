package com.surup.websocket_chat.dto.request;

import jakarta.validation.constraints.Size;

// TODO: 최소값, 패턴 설정 필요
public record UserRegisterRequest(
        @Size(max = 50) String username,
        @Size(max = 100) String password,
        @Size(max = 50) String nickname
) {
    public static UserRegisterRequest of(String username, String password, String nickname) {
        return new UserRegisterRequest(username, password, nickname);
    }
}
