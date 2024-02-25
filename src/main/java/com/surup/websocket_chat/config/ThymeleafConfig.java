package com.surup.websocket_chat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;

@Configuration
public class ThymeleafConfig {

    @Bean
    public SpringResourceTemplateResolver thymeleafTemplateResolver(
            SpringResourceTemplateResolver defaultTemplateResolver
            // Thymeleaf3Properties thymeleaf3Properties
    ) {
        defaultTemplateResolver.setUseDecoupledLogic(true);

        return defaultTemplateResolver;
    }

    //
    // TODO: application.yml 값을 가져오는 과정에서 발생한 error 해결
    //@Getter
    //@RequiredArgsConstructor
    //@ConfigurationProperties("spring.thymeleaf3")
    //public static class Thymeleaf3Properties {
    //    /*
    //     * use thymeleaf decoupled-logic
    //     */
    //    private final boolean decoupledLogic;
    //}
}
