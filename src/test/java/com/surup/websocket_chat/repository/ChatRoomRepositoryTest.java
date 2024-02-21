package com.surup.websocket_chat.repository;

import com.surup.websocket_chat.domain.ChatRoom;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("레포지토리 - 채팅방")
@DataJpaTest
class ChatRoomRepositoryTest {

    private final ChatRoomRepository chatRoomRepository;

    public ChatRoomRepositoryTest(@Autowired ChatRoomRepository chatRoomRepository) {
        this.chatRoomRepository = chatRoomRepository;
    }

    @DisplayName("검색어 없이 데이터 요청 시, 모든 채팅방 리스트를 페이지 크기에 맞게 반환")
    @Test
    void givenNothing_whenSearchingChatRoomTitleAndChatRoomCreatorNickname_thenReturnsSearchedChatRoomList() {
        // Given
        String searchValue = "";
        Pageable pageable = PageRequest.of(0, 10, Sort.by(
                Sort.Order.desc("createdAt")
        ));

        // When
        Page<ChatRoom> chatRoomList = chatRoomRepository
                .findByTitleContainingIgnoreCaseOrUserAccount_NicknameContainingIgnoreCase(searchValue, searchValue, pageable);

        // Then
        assertThat(chatRoomList).hasSize(10);
        assertThat(chatRoomList.getContent().get(0).getTitle()).isEqualTo("채팅방 제목 11");
    }

    @DisplayName("채팅방 제목과 채팅방 방장의 닉네임으로 검색 시, 해당하는 채팅방 리스트를 반환")
    @Test
    void givenTitleAndNickname_whenSearchingChatRoomTitleAndChatRoomCreatorNickname_thenReturnsSearchedChatRoomList() {
        // Given
        String searchValue = "몽총";
        Pageable pageable = PageRequest.of(0, 10, Sort.by(
                Sort.Order.desc("createdAt")
        ));

        // When
        Page<ChatRoom> chatRoomList = chatRoomRepository
                .findByTitleContainingIgnoreCaseOrUserAccount_NicknameContainingIgnoreCase(searchValue, searchValue, pageable);

        // Then
        assertThat(chatRoomList).hasSize(10);
        assertThat(chatRoomList.getContent().get(0).getTitle()).isEqualTo("채팅방 제목 11");
    }
}