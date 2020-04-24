package com.mytechuncle.acnh.controllers;

import com.mytechuncle.acnh.models.ACNHUser;
import com.mytechuncle.acnh.repositories.ACNHUserRepository;
import com.mytechuncle.acnh.services.ACNHUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path="/user")
public class UserController {
    @Autowired
    ACNHUserService service;

    @GetMapping(path = "")
    public ResponseEntity<ACNHUser> getUser(@AuthenticationPrincipal OAuth2User principal) {
        ACNHUser user = service.getUserFromOAuth(principal);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
