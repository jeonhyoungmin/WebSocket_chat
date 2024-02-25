package com.surup.websocket_chat.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

// TODO: 최소값, 패턴 설정 필요
public record UserRegisterRequest(
        @Size(max = 50, min = 5) @NotBlank String username,
        @Size(max = 100, min = 5) @NotBlank String password,
        @Size(max = 50, min = 2) @NotBlank String nickname
) {
    public static UserRegisterRequest of(String username, String password, String nickname) {
        return new UserRegisterRequest(username, password, nickname);
    }
}
