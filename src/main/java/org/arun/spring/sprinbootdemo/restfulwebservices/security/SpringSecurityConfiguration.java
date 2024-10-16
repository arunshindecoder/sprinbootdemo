package org.arun.spring.sprinbootdemo.restfulwebservices.security;

import jakarta.servlet.http.HttpServletResponse;
import org.arun.spring.sprinbootdemo.restfulwebservices.configuration.YamlConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {
// Arun
    private final YamlConfig yamlConfig;
    private final Logger LOGGER = LoggerFactory.getLogger(SpringSecurityConfiguration.class);

    public SpringSecurityConfiguration(YamlConfig yamlConfig) {
        this.yamlConfig = yamlConfig;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.httpBasic(Customizer.withDefaults());
        http.cors(Customizer.withDefaults());
        http.csrf(csrf -> csrf.disable());
        http.sessionManagement(sessionManager -> sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.exceptionHandling(exceptionHandling -> exceptionHandling.authenticationEntryPoint(
                (request, response, exception) -> {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, exception.getMessage());
                }));
        http.authorizeHttpRequests(authorizeHttpRequest -> authorizeHttpRequest
                .requestMatchers("/actuator/health", "/actuator/metrics", "/actuator/metrics/**", "/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html", "/api/monitor/**", "/api/authentication/**")
                .permitAll()
        );
        http.authorizeHttpRequests(authorizeHttpRequest -> authorizeHttpRequest
                .requestMatchers("/hello-world","/hello-world-bean","/filtering","/hello-world/**",
                        "/currency-configuration","/filtering-list","/jpa/**","/person","/person/**"
                        ,"/users","/jpa/users/**","/users/**","/v1/**","/v2/**")
                .permitAll()
        );

        if (yamlConfig.getPublicApiList().size() > 0) {
            http.authorizeHttpRequests(authorizeHttpRequest -> authorizeHttpRequest
                    .requestMatchers(String.join(", ", yamlConfig.getPublicApiList()))
                    .authenticated()

            );
            LOGGER.info("Added public API: '{}' ", String.join("', '", yamlConfig.getPublicApiList()));
        }
//
//        http.authorizeHttpRequests(authorizeHttpRequest -> authorizeHttpRequest
//                .requestMatchers("/hello-world-internationalized")
//                .authenticated()
//
//        );



        //----


        return http.build();
    }

}
