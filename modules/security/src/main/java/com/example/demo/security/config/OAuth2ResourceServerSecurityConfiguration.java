package com.example.demo.security.config;

import com.example.demo.security.SecurityBasePackage;
//import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.security.oauth2.provider.token.store.jwk.JwkTokenStore;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.MappedJwtClaimSetConverter;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

import static org.springframework.http.HttpMethod.*;

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan(basePackageClasses = SecurityBasePackage.class)
//@Profile("oauth")
public class OAuth2ResourceServerSecurityConfiguration {

    private static final String COGNITO_GROUPS = "cognito:groups";
    private static final String SPRING_AUTHORITIES = "authorities";
    private static final String COGNITO_USERNAME = "username";
    private static final String SPRING_USER_NAME = "user_name";

//    @Bean
//    SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
//        http
//                .authorizeExchange()
//                .pathMatchers("/api/**").permitAll()
////            .pathMatchers(POST, "/product-composite/**").hasAuthority("SCOPE_product:write")
////            .pathMatchers(DELETE, "/product-composite/**").hasAuthority("SCOPE_product:write")
////            .pathMatchers(GET, "/product-composite/**").hasAuthority("SCOPE_product:read")
//                .anyExchange().authenticated()
//                .and()
//                .oauth2ResourceServer()
//                .jwt();
//        return http.build();
//    }

    @Bean
    public JwtDecoder customDecoder(OAuth2ResourceServerProperties properties) {
        NimbusJwtDecoder jwtDecoder = NimbusJwtDecoder.withJwkSetUri(
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


//  	@Bean
//	public CorsWebFilter corsFilter() {
//		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		CorsConfiguration config = new CorsConfiguration();
//      config.setAllowedOrigins(Arrays.asList("http://localhost:8081", "http://localhost:4200"));
//		config.setAllowCredentials(true);
//		config.addAllowedOrigin("*");
//		config.addAllowedHeader("*");
//		config.addAllowedMethod("OPTIONS");
//		config.addAllowedMethod("GET");
//		config.addAllowedMethod("POST");
//		config.addAllowedMethod("PUT");
//		config.addAllowedMethod("DELETE");
//		source.registerCorsConfiguration("/**", config);
//		return new CorsWebFilter(source);
//	}

//  @Bean
//  public WebFilter  corsFilter() {
//
//     return new WebFilter() {
//      @Override
//      public Mono<Void> filter (ServerWebExchange exchange, WebFilterChain chain){
//        exchange.getResponse()
//                .getHeaders()
//                .add("Access-Control-Allow-Headers", "*");
//        return chain.filter(exchange);
//      }
//    };
//  }


    @Bean
    SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
                .authorizeExchange()
                .pathMatchers("/api/**").permitAll()
//            .pathMatchers(POST, "/product-composite/**").hasAuthority("SCOPE_product:write")
//            .pathMatchers(DELETE, "/product-composite/**").hasAuthority("SCOPE_product:write")
//            .pathMatchers(GET, "/product-composite/**").hasAuthority("SCOPE_product:read")
                .anyExchange().authenticated()
                .and()
                .oauth2ResourceServer()
                .jwt();
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
