package com.example.demo.security.config;

import com.example.demo.security.SecurityBasePackage;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.jwk.JwkTokenStore;

import java.util.Collections;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan(basePackageClasses = SecurityBasePackage.class)
@EnableResourceServer
@Profile("oauth")
public class OAuth2ResourceServerSecurityConfiguration extends ResourceServerConfigurerAdapter {

    private final ResourceServerProperties resource;

    public OAuth2ResourceServerSecurityConfiguration(ResourceServerProperties resource) {
        this.resource = resource;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.cors();

        http.csrf().disable();

        http.authorizeRequests()
            .antMatchers("/actuator/**")
            .permitAll()
            .antMatchers("/api/**")
            .permitAll()
            .anyRequest()
            .authenticated();
    }

    @Bean
    public TokenStore jwkTokenStore() {
        return new JwkTokenStore(
                Collections.singletonList(resource.getJwk().getKeySetUri()),
                new CognitoAccessTokenConverter(),
                null);
    }
}
