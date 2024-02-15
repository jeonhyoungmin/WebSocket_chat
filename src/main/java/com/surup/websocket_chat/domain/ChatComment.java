package com.surup.websocket_chat.domain;

import com.surup.websocket_chat.repository.ChatCommentRepository;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Getter
@ToString
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
    @Setter @JoinColumn(name = "roomId") @ManyToOne(optional = false, fetch = FetchType.LAZY) private ChatRoom chatRoom;
    @Setter @JoinColumn(name = "userId") @ManyToOne(optional = false, fetch = FetchType.LAZY) private UserAccount userAccount;
    @Setter @Column(nullable = false, length = 1000) private String content;

    protected ChatComment(){}

    protected ChatComment(ChatRoom chatRoom, UserAccount userAccount, String content) {
        this.chatRoom = chatRoom;
        this.userAccount = userAccount;
        this.content = content;
    }

    public static ChatComment of(ChatRoom chatRoom, UserAccount userAccount, String content) {
        return new ChatComment(chatRoom, userAccount, content);
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
