package com.surup.websocket_chat.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@ToString
/*
    TODO : 사용 여부 검토
    @Table(indexes = {
            @Index(columnList = "title"),
            @Index(columnList = "createdAt")
    })
 */
@Entity
public class ChatRoom extends AuditingFields {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Setter @JoinColumn(name = "userId") @ManyToOne(optional = false, fetch = FetchType.LAZY) private UserAccount userAccount;
    @Setter @Column(nullable = false) private String title;
    @Setter @Column(length = 100) private String password;
    @Setter @Column(nullable = false) private Integer count;

    @ToString.Exclude
    @OrderBy("id")
    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL)
    private final Set<ChatComment> chatComments = new LinkedHashSet<>();

    protected ChatRoom(){}

    protected ChatRoom(UserAccount userAccount, String title, String password) {
        this.userAccount = userAccount;
        this.title = title;
        this.password = password;
    }

    public static ChatRoom of(UserAccount userAccount, String title, String password) {
        return new ChatRoom(userAccount, title, password);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChatRoom chatRoom)) return false;
        return id != null && id.equals(chatRoom.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
