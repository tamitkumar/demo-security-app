package com.org.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated() // Ensure all requests require authentication
                )
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwtConfigurer ->
                        jwtConfigurer.jwtAuthenticationConverter(jwtAuthenticationConverter()))
                );
        return http.build();
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        grantedAuthoritiesConverter.setAuthorityPrefix("SCOPE_");
        JwtAuthenticationConverter authenticationConverter = new JwtAuthenticationConverter();
        authenticationConverter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
        authenticationConverter.setJwtGrantedAuthoritiesConverter(jwt -> {
            List<String> audience = jwt.getClaimAsStringList("aud");
            if (audience == null || !audience.contains("s1-client-id")) {
                throw new SecurityException("Invalid token audience");
            }
            return grantedAuthoritiesConverter.convert(jwt);
        });
        return authenticationConverter;
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withJwkSetUri("https://localhost:8081/oauth2/jwks").build();
    }
}
