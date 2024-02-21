package com.surup.websocket_chat.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*
  @WebMvcTest(MainController.class) - spring security 에 영향받는 테스트
  @WithMockUser("user1")            - spring security 에 인증된 사용자 추가하여 unauthorized error 회피
  적용한 설정                         - spring security 설정 제외
 */
@DisplayName("컨트롤러 - 메인")
@WebMvcTest(value = MainController.class,
        excludeAutoConfiguration = SecurityAutoConfiguration.class // spring security 의 기본 설정을 적용해주는 설정 제외
        // excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class))
)
class MainControllerTest {

  private final MockMvc mvc;

  public MainControllerTest(@Autowired MockMvc mvc) {
    this.mvc = mvc;
  }

  @DisplayName("[VIEW][GET] 루트 URI 요청 시, /chatRoomList 로 redirection")
  @Test
  void givenNothing_whenRootURIRequest_thenReturnRedirectingChatRoomList() throws Exception {
    // Given

    // When & Then
    mvc.perform(get("/"))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/chatRoomList"));
  }
}