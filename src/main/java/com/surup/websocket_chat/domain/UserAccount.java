package com.surup.websocket_chat.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@ToString
@Table(indexes = {
        @Index(columnList = "email", unique = true),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
@EntityListeners(AuditingEntityListener.class)
@Entity
public class UserAccount {
    @Id
    @Column(length = 50)
    private String userId; // 유저 id

    @Setter @Column(nullable = false) private String userPassword; // 유저 암호

    @Setter private String email; // 이메일
    @Setter private String nickname; // 닉네임
    @Setter private String memo; // 메모

    @CreatedDate @Column(nullable = false) private LocalDateTime createdAt; // 생성 일시
    @CreatedBy @Column(nullable = false, length = 50) private String createdBy; // 생성자
    @LastModifiedDate @Column(nullable = false) private LocalDateTime modifiedAt; // 수정 일시
    @LastModifiedBy @Column(nullable = false, length = 50) private String modifiedBy; // 수정자

    protected UserAccount() {} // Hibernate JPA 기본 생성자 필요

    public UserAccount(String userId, String userPassword) {
        this.userId = userId;
        this.userPassword = userPassword;
    }

    private UserAccount(String userId, String userPassword, String email, String nickname, String memo, String createdBy) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.email = email;
        this.nickname = nickname;
        this.memo = memo;
        this.createdBy = createdBy;
        this.modifiedBy = createdBy;
    }

    public static UserAccount of(String userId, String userPassword) {
        return new UserAccount(userId, userPassword);
    }

    public static UserAccount of(String userId, String userPassword, String email, String nickname, String memo) {
        return new UserAccount(userId, userPassword, email, nickname, memo, null);
    }

    public static UserAccount of(String userId, String userPassword, String email, String nickname, String memo, String createdBy) {
        return new UserAccount(userId, userPassword, email, nickname, memo, createdBy);
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
