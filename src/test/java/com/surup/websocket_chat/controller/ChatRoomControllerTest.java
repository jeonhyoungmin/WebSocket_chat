package com.surup.websocket_chat.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.surup.websocket_chat.dto.UserAccountDto;
import com.surup.websocket_chat.dto.request.ChatRoomPasswordRequest;
import com.surup.websocket_chat.dto.request.CreateChatRoomRequest;
import com.surup.websocket_chat.dto.request.UserRegisterRequest;
import com.surup.websocket_chat.dto.response.ChatRoomResponse;
import com.surup.websocket_chat.service.ChatRoomService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("컨트롤러 - 채팅방")
@WebMvcTest(ChatRoomController.class)
@WithMockUser("USER1") // spring security 에 인증된 사용자 추가하여 unauthorized error 회피
class ChatRoomControllerTest {

    private final MockMvc mvc;
    private final ObjectMapper objectMapper;

    @MockBean private ChatRoomService chatRoomService;

    public ChatRoomControllerTest(@Autowired MockMvc mvc,
                                  @Autowired ObjectMapper objectMapper
    ) {
        this.mvc = mvc;
        this.objectMapper = objectMapper;
    }

    @DisplayName("[VIEW][GET] 채팅방 리스트 요청 시, 채팅방 최근 생성 일자 순으로 뷰로 전달")
    @Test
    void givenNothing_whenChatRoomListURIRequest_thenReturnsChatRoomListViewWithChatRoomList() throws Exception {
        // Given

        // When & Then
        mvc.perform(get("/chatRoomList"))
                .andExpect(status().isOk())
                .andExpect(view().name("/chatRoomList"))
                .andExpect(model().hasNoErrors())
                .andExpect(model().attributeExists("chatRoomList"))
                .andDo(print());
    }

    @DisplayName("[VIEW][POST] 채팅방 입장 요청 시, 채팅방 id 일치하는 채팅방으로 이동")
    @Test
    void givenChatRoomIdAndPassword_whenChatRoomURIRequest_thenReturnsViewChatRoomWithIDMatchingChatRoom() throws Exception {
        // Given
        String content = objectMapper.writeValueAsString(
                ChatRoomPasswordRequest.of(1L, "")
        );
        UserAccountDto userAccountDto = UserAccountDto.of(12L, "test", "test", "몽총이 12");

        // When & Then
        mvc.perform(post("/enterChatRoom")
                        .contentType(MediaType.MULTIPART_FORM_DATA)
//                        .content(content)
                        .with(csrf())
                        .with(SecurityMockMvcRequestPostProcessors.user(userAccountDto))
                )
                .andExpect(status().isOk())
                .andExpect(view().name("/chatRoom"))
                .andExpect(model().hasNoErrors())
//                .andExpect(model().attributeExists("chatRoomId")) TODO: DTO 객체로 받아서 model 전달
                .andExpect(model().attributeExists("userNickname"))
                .andDo(print());
    }

    @Disabled("인가 에러 해결 필요") // TODO: 인가 오류 해결
    @DisplayName("[CREATE][POST] 채팅방 생성 요청 시, 채팅방 생성 후 해당 방으로 이동")
    @Test
    void givenChatRoomTitleAndPasswordOrNot_whenRequestCreateChatRoom_thenCreateChatRoomAndRedirectionToChatRoom() throws Exception {
        // Given
        String content = objectMapper.writeValueAsString(
                CreateChatRoomRequest.of("chatRoomTitle", "password")
        );
        UserAccountDto userAccountDto = UserAccountDto.of(12L, "test", "test", "몽총이 12");

        // When & Then
        mvc.perform(post("/createChatRoom")
                        .contentType(MediaType.MULTIPART_FORM_DATA)
//                        .content(content)
                        .with(csrf())
                        .with(SecurityMockMvcRequestPostProcessors.user(userAccountDto))
                )
                .andExpect(status().isOk())
                .andExpect(view().name("/chatRoom"))
                .andExpect(model().hasNoErrors())
                .andDo(print());
    }

}