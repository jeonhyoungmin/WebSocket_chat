package com.surup.websocket_chat.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Getter
@ToString
// @Table(indexes = @Index(columnList = "createdAt")) TODO : 사용 여부 검토
@Entity
public class UserAccount extends AuditingFields {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Setter @Column(nullable = false, unique = true, length = 50) private String userName;
    @Setter @Column(nullable = false, length = 100) private String userPassword;
    @Setter @Column(nullable = false, unique = true, length = 50) private String nickname;

    protected UserAccount(){}

    protected UserAccount(String userName, String userPassword, String nickname) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.nickname = nickname;
    }

    public static UserAccount of(String userName, String userPassword, String nickname) {
        return new UserAccount(userName, userPassword, nickname);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserAccount userAccount)) return false;
        return id != null && id.equals(userAccount.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
