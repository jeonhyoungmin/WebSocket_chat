package com.surup.websocket_chat.repository;

import com.surup.websocket_chat.domain.ChatRoom;
import com.surup.websocket_chat.dto.ChatRoomDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    Optional<ChatRoom> findByIdAndPassword(Long id, String password);
}
