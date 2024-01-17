package com.surup.websocket_chat.controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Disabled("Spring Data REST 통합테스트는 불필요하므로 제외시킴")
@DisplayName("Data REST - API Test")
@Transactional // 기본값 = rollback
@AutoConfigureMockMvc
//@WebMvcTest data rest auto configuration 을 읽어오지 않기 때문에 슬라이스 검사 진행 불가
@SpringBootTest // 통합 검사 (DB에 영향을 주기 때문에 @Transactional 애너테이션 추가)
public class DataRestTest {

    private final MockMvc mvc;

    public DataRestTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }

    @DisplayName("[api] 유저 리스트 조회")
    @Test
    void givenNothing_whenRequestingUserAccounts_thenReturnsUserAccountsJsonResponse() throws Exception {
        // Given

        // When & Then
        mvc.perform(get("/api/userAccounts"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
    }

    @DisplayName("[api] 유저 단일 조회")
    @Test
    void givenNothing_whenRequestingUserAccount_thenReturnsUserAccountJsonResponse() throws Exception {
        // Given

        // When & Then
        mvc.perform(get("/api/userAccounts/Arliene"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")))
                .andDo(print());
    }

    @DisplayName("[api] 채팅방 단일 조회")
    @Test
    void givenNothing_whenRequestingChatRoom_thenReturnsChatRoomJsonResponse() throws Exception {
        // Given

        // When & Then
        mvc.perform(get("/api/chatRooms/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
    }

    @DisplayName("[api] 채팅방의 채팅 조회")
    @Test
    void givenNothing_whenRequestingChatRoomChatComments_thenReturnsChatRoomChatCommentsJsonResponse() throws Exception {
        // Given

        // When & Then
        mvc.perform(get("/api/chatRooms/1/chatComments"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
    }

    @DisplayName("[api] 모든 채팅 리스트 조회")
    @Test
    void givenNothing_whenRequestingChatComments_thenReturnsChatCommentsJsonResponse() throws Exception {
        // Given

        // When & Then
        mvc.perform(get("/api/chatComments"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
    }

    @DisplayName("[api] 채팅 단일 조회")
    @Test
    void givenNothing_whenRequestingChatComment_thenReturnsChatCommentJsonResponse() throws Exception {
        // Given

        // When & Then
        mvc.perform(get("/api/chatComments/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
    }
}
