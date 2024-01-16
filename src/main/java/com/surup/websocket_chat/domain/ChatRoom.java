package com.surup.websocket_chat.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@ToString
@Table(indexes = {
        @Index(columnList = "title"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
@Entity
public class ChatRoom extends AuditingFields {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 채팅방 id

    @Setter
    @ManyToOne(optional = false) // optional = flase: FK에 not null ddl문 발생
    private UserAccount userAccount; // 유저 계정 (id)

    @Setter private String title; // 채팅방 제목
    @Setter private String password; // 채팅방 암호

    // @ToString.Exclude TODO: 미적용해도 괜찮은지 체크
    @OrderBy("id")
    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL)
    private final Set<ChatComment> chatComments = new LinkedHashSet<>(); // chatRoom에 대한 채팅 내용을 Set에 담기

    protected ChatRoom() {}

    private ChatRoom(UserAccount userAccount, String title, String password) {
        this.userAccount = userAccount;
        this.title = title;
        this.password = password;
    }

    public static ChatRoom of(UserAccount userAccount) {
        return new ChatRoom(userAccount, "", ""); // TODO: 방 이름을 설정하지 않으면, userId로 설정되도록 변경
    }

    public static ChatRoom of(UserAccount userAccount, String title) {
        return new ChatRoom(userAccount, title, "");
    }

    public static ChatRoom of(UserAccount userAccount, String title, String password) {
        return new ChatRoom(userAccount, title, password);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChatRoom chatRoom)) return false;  // pattern variable 적용
        return id != null && id.equals(chatRoom.id); // 영속화되지 않은 entity는 모든 동등성 검사를 탈락한다.
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
