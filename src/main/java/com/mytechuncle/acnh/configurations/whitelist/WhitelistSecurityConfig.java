package com.mytechuncle.acnh.configurations.whitelist;

import com.mytechuncle.acnh.configurations.ConsentingOAuth2AuthorizationRequestResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestRedirectFilter;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Profile("whitelist")
@Configuration
public class WhitelistSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    Whitelist whitelist;

    @Autowired
    private ClientRegistrationRepository clientRegistrationRepository;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
                .antMatchers("/locales/**")
                .antMatchers("/manifest.json")
                .antMatchers("/service-worker.js");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf(c -> c
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                )
                .logout().clearAuthentication(true).and()
                .addFilterAfter(new WhitelistSecurityFilter(whitelist.getEmails()), OAuth2LoginAuthenticationFilter.class)
                .authorizeRequests(a -> a
                        .antMatchers("/oauth2/**").permitAll()
                        .antMatchers("/login/**").permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2Login()
                    .defaultSuccessUrl("/", true)
                    .authorizationEndpoint()
                        .authorizationRequestResolver(new ConsentingOAuth2AuthorizationRequestResolver(clientRegistrationRepository));

    }
}
