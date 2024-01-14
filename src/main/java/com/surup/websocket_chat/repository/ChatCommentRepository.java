package com.surup.websocket_chat.repository;

import com.surup.websocket_chat.domain.ChatComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatCommentRepository extends JpaRepository<ChatComment, Long> {
}
