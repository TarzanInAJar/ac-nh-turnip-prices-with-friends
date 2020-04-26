package com.mytechuncle.acnh.services;

import com.mytechuncle.acnh.models.ACNHUser;
import com.mytechuncle.acnh.models.TurnipWeek;
import com.mytechuncle.acnh.repositories.ACNHUserRepository;
import com.mytechuncle.acnh.repositories.TurnipWeekRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ACNHUserService {
    @Autowired
    ACNHUserRepository acnhUserRepository;

    @Autowired
    TurnipWeekRepository turnipWeekRepository;

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

    public TurnipWeek getTurnipWeek(ACNHUser user, int year, int week) {
        Optional<TurnipWeek> optionalTurnipWeek = turnipWeekRepository.findByUserAndYearAndWeek(user, year, week);
        if (!optionalTurnipWeek.isPresent()) {
            TurnipWeek turnipWeek = new TurnipWeek();
            turnipWeek.setUser(user);
            turnipWeek.setYear(year);
            turnipWeek.setWeek(week);
            return turnipWeekRepository.save(turnipWeek);
        }
        return optionalTurnipWeek.get();
    }
}
