package com.surup.websocket_chat.repository;

import com.surup.websocket_chat.config.JpaConfig;
import com.surup.websocket_chat.domain.ChatRoom;
import com.surup.websocket_chat.domain.UserAccount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("JPA connection test")
@Import(JpaConfig.class) // Test 가 직접 만든 config 를 읽을 수 있도록 import
@DataJpaTest
class JpaRepositoryTest {

    private final UserAccountRepository userAccountRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatCommentRepository chatCommentRepository;

    public JpaRepositoryTest(@Autowired UserAccountRepository userAccountRepository,
                             @Autowired ChatRoomRepository chatRoomRepository,
                             @Autowired ChatCommentRepository chatCommentRepository) {
        this.userAccountRepository = userAccountRepository;
        this.chatRoomRepository = chatRoomRepository;
        this.chatCommentRepository = chatCommentRepository;
    }

    @DisplayName("select test")
    @Test
    void givenTestData_whenSelecting_thenWorksFine() {
        // Given

        // When
        List<UserAccount> userAccounts = userAccountRepository.findAll();

        // Then
        assertThat(userAccounts)
                .isNotNull()
                .hasSize(30);
    }

    @DisplayName("insert test")
    @Test
    void givenTestData_whenInserting_thenWorksFine() {
        // Given
        long previousCount = userAccountRepository.count();

        // When
        UserAccount savedUserAccount = userAccountRepository.save(UserAccount.of("happy", "fdse2512@!"));

        // Then
        assertThat(userAccountRepository.count()).isEqualTo(previousCount + 1);
    }

    @DisplayName("update test")
    @Test
    void givenTestData_whenUpdating_thenWorksFine() {
        // Given
        UserAccount userAccount = userAccountRepository.findById("Florence").orElseThrow();
        String updateNickName = "happyhappy";
        userAccount.setNickname(updateNickName);

        // When
        UserAccount savedUserAccount = userAccountRepository.saveAndFlush(userAccount);

        // Then
        assertThat(savedUserAccount).hasFieldOrPropertyWithValue("nickname", "happyhappy");
    }

    @DisplayName("delete test")
    @Test
    void givenTestData_whenDeleting_thenWorksFine() {
        // Given
        ChatRoom chatRoom = chatRoomRepository.findById(1L).orElseThrow();
        long previousChatRoomCount = chatRoomRepository.count();
        long previousChatComment = chatCommentRepository.count();
        int deletedCommentsSize = chatRoom.getChatComments().size();

        // When
        chatRoomRepository.delete(chatRoom);

        // Then
        assertThat(chatRoomRepository.count()).isEqualTo(previousChatRoomCount - 1);
        assertThat(chatCommentRepository.count()).isEqualTo(previousChatComment - deletedCommentsSize);
    }

}