package com.mytechuncle.acnh;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import java.util.List;

@SpringBootApplication
@EnableWebSecurity
public class TurnipPricesWithFriendsApplication {

    public static void main(String[] args) {
        SpringApplication.run(TurnipPricesWithFriendsApplication.class, args);
    }

}
