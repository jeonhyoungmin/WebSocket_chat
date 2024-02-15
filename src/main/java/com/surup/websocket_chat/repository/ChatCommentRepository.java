package com.surup.websocket_chat.repository;

import com.surup.websocket_chat.domain.ChatComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

public interface ChatCommentRepository extends JpaRepository<ChatComment, Long> {
}
