package com.surup.websocket_chat.dto.request;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.Max;

public record CreateChatRoomRequest(
        @Max(255) @Nonnull String title,
        @Max(100) String password
) {
    public static CreateChatRoomRequest of(String title, String password) {
        return new CreateChatRoomRequest(title, password);
    }

}
