package com.surup.websocket_chat.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.surup.websocket_chat.dto.request.ChatRoomPasswordRequest;
import com.surup.websocket_chat.dto.request.UserRegisterRequest;
import com.surup.websocket_chat.service.UserAccountService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@DisplayName("컨트롤러 - 사용자 계정")
@WebMvcTest(AuthController.class)
@WithMockUser("USER1")
class AuthControllerTest {

    private final MockMvc mvc;
    private final ObjectMapper objectMapper;

    @MockBean private UserAccountService userAccountService;

    public AuthControllerTest(@Autowired MockMvc mvc,
                              @Autowired ObjectMapper objectMapper) {
        this.mvc = mvc;
        this.objectMapper = objectMapper;
    }

    @DisplayName("[CREATE][POST] 회원가입 요청 시, 유저 등록 후 login page 이동")
    @Test
    void givenRegisterInfo_whenRequestUserRegister_thenUserRegisterAndRedirectLoginPage() throws Exception {
        // Given
        String content = objectMapper.writeValueAsString(
                UserRegisterRequest.of("TestID", "TestPassword", "nickname")
        );

        // When & Then
        mvc.perform(post("/signup")
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                        .content(content)
                        .with(csrf())
                )
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andDo(print());
    }

}