package com.surup.websocket_chat.repository;

import com.surup.websocket_chat.domain.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccount, String> {
}
