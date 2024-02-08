package com.surup.websocket_chat.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Getter
@ToString
@NoArgsConstructor
/*
    TODO : 사용 여부 검토
    @Table(indexes = {
        @Index(columnList = "content"),
        @Index(columnList = "createdAt"),
     })
 */
@Entity
public class ChatComment extends AuditingFields {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Setter @ManyToOne(optional = false) private ChatRoom chatRoom;
    @Setter @ManyToOne(optional = false) private UserAccount userAccount;
    @Setter @Column(nullable = false, length = 50) String nickname;
    @Setter @Column(nullable = false, length = 1000) private String content;

    public ChatComment(ChatRoom chatRoom, UserAccount userAccount, String nickname, String content) {
        this.chatRoom = chatRoom;
        this.userAccount = userAccount;
        this.nickname = nickname;
        this.content = content;
    }

    public static ChatComment of(ChatRoom chatRoom, UserAccount userAccount, String nickname, String content) {
        return new ChatComment(chatRoom, userAccount, nickname, content);
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
