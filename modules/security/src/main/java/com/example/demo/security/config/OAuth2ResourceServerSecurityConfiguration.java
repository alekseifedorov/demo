package com.example.demo.security.config;

import com.example.demo.security.SecurityBasePackage;
//import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Map;

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
@ComponentScan(basePackageClasses = SecurityBasePackage.class)
//@Profile("oauth")
public class OAuth2ResourceServerSecurityConfiguration {

    private static final String COGNITO_GROUPS = "cognito:groups";
    private static final String SPRING_AUTHORITIES = "authorities";
    private static final String COGNITO_USERNAME = "username";
    private static final String SPRING_USER_NAME = "user_name";

    @Bean
    public ReactiveJwtDecoder customDecoder(OAuth2ResourceServerProperties properties) {
        NimbusReactiveJwtDecoder jwtDecoder = NimbusReactiveJwtDecoder.withJwkSetUri(
                properties.getJwt().getJwkSetUri()).build();

        jwtDecoder.setClaimSetConverter(new CognitoConverter());
        return jwtDecoder;
    }

    public class CognitoConverter implements
            Converter<Map<String, Object>, Map<String, Object>> {

        public Map<String, Object> convert(Map<String, Object> claims) {
            if (claims.containsKey(COGNITO_GROUPS)) {
                claims.put(SPRING_AUTHORITIES, claims.get(COGNITO_GROUPS));
            }
            if (claims.containsKey(COGNITO_USERNAME)) {
                claims.put(SPRING_USER_NAME, claims.get(COGNITO_USERNAME));
            }
            return claims;
        }
    }

    @Bean
    SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http, OAuth2ResourceServerProperties properties) {
        http.csrf().disable()
                .authorizeExchange()
               // .pathMatchers("/api/**").permitAll()//.hasAuthority("ROLE_ADMIN")
//            .pathMatchers(POST, "/product-composite/**").hasAuthority("SCOPE_product:write")
//            .pathMatchers(DELETE, "/product-composite/**").hasAuthority("SCOPE_product:write")
//            .pathMatchers(GET, "/product-composite/**").hasAuthority("SCOPE_product:read")
                .anyExchange().authenticated()
                .and()
                .oauth2ResourceServer()
                .jwt().jwtDecoder(customDecoder(properties));
        return http.build();
    }


    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("authorization", "content-type", "x-auth-token"));
        configuration.setExposedHeaders(Arrays.asList("x-auth-token"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
