package com.surup.websocket_chat.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
// @Table(indexes = @Index(columnList = "createdAt")) TODO : 사용 여부 검토
@Entity
public class UserAccount extends AuditingFields {

    @Id @Column(length = 50) private String userId;
    @Setter @Column(nullable = false, length = 100) private String userPassword;
    @Setter @Column(nullable = false, length = 50) private String nickname;

    public static UserAccount of(String userId, String userPassword, String nickname) {
        return new UserAccount(userId, userPassword, nickname);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserAccount userAccount)) return false;
        return userId != null && userId.equals(userAccount.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
}
