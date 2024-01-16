package com.surup.websocket_chat.repository;

import com.surup.websocket_chat.domain.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
}
