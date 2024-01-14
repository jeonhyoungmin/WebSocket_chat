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
        @Index(columnList = "content"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
@EntityListeners(AuditingEntityListener.class)
@Entity
public class ChatComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 채팅 id

    @Setter @ManyToOne(optional = false) private ChatRoom chatRoom; // 채팅방 (id)
    @Setter @ManyToOne(optional = false) private UserAccount userAccount; // 유저 계정 (id)
    @Setter @Column(updatable = false) private Long parentCommentId; // 부모 채팅 id (채팅 댓글 바로가기 기능)

    @Setter @Column(length = 1000) private String content; // 본문

    @CreatedDate @Column(nullable = false) private LocalDateTime createdAt; // 생성 일시
    @CreatedBy @Column(nullable = false, length = 50) private String createdBy; // 생성자
    @LastModifiedDate @Column(nullable = false) private LocalDateTime modifiedAt; // 수정 일시
    @LastModifiedBy @Column(nullable = false, length = 50) private String modifiedBy; // 수정자

    protected ChatComment() {}

    private ChatComment(ChatRoom chatRoom, UserAccount userAccount, Long parentCommentId, String content) {
        this.chatRoom = chatRoom;
        this.userAccount = userAccount;
        this.parentCommentId = parentCommentId;
        this.content = content;
    }

    public static ChatComment of(ChatRoom chatRoom, UserAccount userAccount, Long parentCommentId, String content) {
        return new ChatComment(chatRoom, userAccount, parentCommentId, content);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChatComment chatComment)) return false;
        return id != null && id.equals(chatComment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
