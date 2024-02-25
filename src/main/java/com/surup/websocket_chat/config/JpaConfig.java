package com.surup.websocket_chat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@Configuration
public class JpaConfig {

//    @Bean
//    public AuditorAware<String> auditorAware() { // TODO: 사용 여부 검토
//        return () -> Optional.ofNullable(SecurityContextHolder.getContext())
//                .map(SecurityContext::getAuthentication)
//                .filter(Authentication::isAuthenticated)
//                .map(Authentication::getPrincipal)
//                .map(UserAccountDto.class::cast)
//                .map(UserAccountDto::getUsername);
//    }
}
