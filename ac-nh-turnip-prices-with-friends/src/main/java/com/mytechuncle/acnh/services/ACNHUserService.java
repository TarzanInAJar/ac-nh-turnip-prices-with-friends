package com.mytechuncle.acnh.services;

import com.mytechuncle.acnh.models.ACNHUser;
import com.mytechuncle.acnh.repositories.ACNHUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ACNHUserService {
    @Autowired
    ACNHUserRepository acnhUserRepository;

    public ACNHUser getUserFromOAuth(OAuth2User user) {
        String email = user.getAttribute("email");
        Optional<ACNHUser> optionalUser = acnhUserRepository.findUserByEmail(email);
        if (!optionalUser.isPresent()) {
            ACNHUser acnhUser = new ACNHUser();
            acnhUser.setEmail(email);
            acnhUser.setName(user.getAttribute("name"));
            return acnhUserRepository.save(acnhUser);
        }
        return optionalUser.get();
    }
}
