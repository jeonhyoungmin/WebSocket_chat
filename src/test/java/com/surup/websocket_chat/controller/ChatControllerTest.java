package com.surup.websocket_chat.controller;

import com.surup.websocket_chat.service.ChatRoomService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ChatController.class)
@WithMockUser("user1") // spring security 에 인증된 사용자 추가하여 unauthorized error 회피
class ChatControllerTest {

    private final MockMvc mvc;

    @MockBean
    ChatRoomService chatRoomService;

    public ChatControllerTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }

    @DisplayName("[view][GET] 채팅방 리스트 요청 시, 채팅방 최근 생성 일자 순으로 뷰로 전달")
    @Test
    void givenNothing_whenChatRoomListURIRequest_thenReturnsChatRoomListViewWithChatRoomList() throws Exception {
        // Given

        // When & Then
        mvc.perform(get("/chatRoomList"))
                .andExpect(status().isOk())
                .andExpect(view().name("chatRoomList"))
                .andExpect(model().hasNoErrors())
                .andExpect(model().attributeExists("chatRoom"))
                .andDo(print());
    }

    @DisplayName("[view][GET] 채팅방 요청 시, 채팅방 id 일치하는 채팅방으로 이동")
    @Test
    void givenChatRoomIdAndPassword_whenChatRoomURIRequest_thenReturnsViewChatRoomWithChatRoom() throws Exception {
        // Given

        // When & Then
        mvc.perform(get("/chatRoom")
                        .queryParam("id", "1")
                        .queryParam("password", "password")
                )
                .andExpect(status().isOk())
                .andDo(print());
    }
}