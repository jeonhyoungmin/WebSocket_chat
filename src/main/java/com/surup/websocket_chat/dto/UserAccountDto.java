package com.surup.websocket_chat.dto;

import com.surup.websocket_chat.domain.UserAccount;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

/*
    TODO: 권한, 만료 기능 사용 추후 검토
 */
public record UserAccountDto(
        Long id,
        String username,
        String password,
        String nickname,
        Collection<? extends GrantedAuthority> authorities
)  implements UserDetails {

    public static UserAccountDto of(Long id, String username, String password, String nickname) {
        Set<RoleType> roleType = Set.of(RoleType.USER);
        return new UserAccountDto(
                id,
                username,
                password,
                nickname,
                roleType.stream()
                        .map(RoleType::getName)
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toUnmodifiableSet())
        );
    }

    public static UserAccountDto from(UserAccount userAccount) {
        return UserAccountDto.of(
                userAccount.getId(),
                userAccount.getUserName(),
                userAccount.getUserPassword(),
                userAccount.getNickname()
        );
    }

    @Override public String getPassword() { return password; }
    @Override public String getUsername() { return username; }
    @Override public Collection<? extends GrantedAuthority> getAuthorities() { return authorities; }

    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }

    public enum RoleType {
        USER("ROLE_USER");

        @Getter private final String name;

        RoleType(String name){
            this.name = name;
        }
    }
}
