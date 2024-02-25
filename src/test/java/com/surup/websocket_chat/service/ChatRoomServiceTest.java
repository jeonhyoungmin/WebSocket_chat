package com.surup.websocket_chat.service;

import com.surup.websocket_chat.dto.ChatRoomDto;
import com.surup.websocket_chat.dto.response.ChatRoomResponse;
import com.surup.websocket_chat.repository.ChatRoomRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@DisplayName("비즈니스 로직 - 채팅방")
@ExtendWith(MockitoExtension.class)
class ChatRoomServiceTest {

    @InjectMocks ChatRoomService chatRoomService;
    @Mock ChatRoomRepository chatRoomRepository;

    @DisplayName("검색어 없이 채팅방 리스트 요청하면, 채팅방 리스트를 반환한다.")
    @Test
    void givenNothing_whenRequestChatRoomList_thenReturnsChatRoomList() {
        // Given
        String searchValue = "채팅";
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Order.desc("createdAt")));
        given(chatRoomRepository
                .findByTitleContainingIgnoreCaseOrUserAccount_NicknameContainingIgnoreCase(searchValue, searchValue, pageable))
                .willReturn(Page.empty());

        // When
        Page<ChatRoomResponse> chatRoomList = chatRoomService.getChatRoomList(searchValue, pageable);

        // Then
        assertThat(chatRoomList).isEmpty();
        then(chatRoomRepository).should()
                .findByTitleContainingIgnoreCaseOrUserAccount_NicknameContainingIgnoreCase(searchValue, searchValue, pageable);
    }
}