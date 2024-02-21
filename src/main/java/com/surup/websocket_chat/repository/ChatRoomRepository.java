package com.surup.websocket_chat.repository;

import com.surup.websocket_chat.domain.ChatRoom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    Optional<ChatRoom> findByIdAndPassword(Long id, String password);

    Page<ChatRoom> findByTitleContainingIgnoreCaseOrUserAccount_NicknameContainingIgnoreCase(String title, String nickname, Pageable pageable);
}
