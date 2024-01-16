package com.surup.websocket_chat.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Getter
@ToString
@Table(indexes = {
        @Index(columnList = "email", unique = true),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
@Entity
public class UserAccount extends AuditingFields {
    @Id
    @Column(length = 50)
    private String userId; // 유저 id

    @Setter @Column(nullable = false) private String userPassword; // 유저 암호

    @Setter private String email; // 이메일
    @Setter private String nickname; // 닉네임
    @Setter private String memo; // 메모

    protected UserAccount() {} // Hibernate JPA 기본 생성자 필요

    public UserAccount(String userId, String userPassword) {
        this.userId = userId;
        this.userPassword = userPassword;
    }

    private UserAccount(String userId, String userPassword, String email, String nickname, String memo) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.email = email;
        this.nickname = nickname;
        this.memo = memo;
    }

    public static UserAccount of(String userId, String userPassword) {
        return new UserAccount(userId, userPassword);
    }

    public static UserAccount of(String userId, String userPassword, String email, String nickname, String memo) {
        return new UserAccount(userId, userPassword, email, nickname, memo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserAccount userAccount)) return false;  // pattern variable 적용
        return userId != null && userId.equals(userAccount.userId); // 영속화되지 않은 entity는 모든 동등성 검사를 탈락한다.
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
}
