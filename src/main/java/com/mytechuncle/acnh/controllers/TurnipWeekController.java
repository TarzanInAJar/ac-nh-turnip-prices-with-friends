package com.mytechuncle.acnh.controllers;

import com.mytechuncle.acnh.models.ACNHUser;
import com.mytechuncle.acnh.models.TurnipWeek;
import com.mytechuncle.acnh.models.ui.TurnipWeekLocalStorage;
import com.mytechuncle.acnh.services.ACNHUserService;
import com.mytechuncle.acnh.services.TurnipDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Calendar;
import java.util.Locale;

@Controller
@RequestMapping("/turnips")
public class TurnipWeekController {

    @Autowired
    ACNHUserService userService;

    @Autowired
    TurnipDTOService turnipService;

    @GetMapping("")
    public ResponseEntity<TurnipWeek> getCurrentTurnipWeek(@AuthenticationPrincipal OAuth2User user) {
        ACNHUser acnhUser = userService.getUserFromOAuth(user);
        LocalDate localDate = LocalDate.now();
        TemporalField woy = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
        int weekNumber = localDate.get(woy);
        TurnipWeek turnipWeek = userService.getTurnipWeek(acnhUser, localDate.getYear(), weekNumber);
        return new ResponseEntity<>(turnipWeek, HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<Void> updateTurnipWeek(@AuthenticationPrincipal OAuth2User user, @RequestBody TurnipWeekLocalStorage localStorage) {
        ACNHUser acnhUser = userService.getUserFromOAuth(user);
        LocalDate localDate = LocalDate.now();
        TemporalField woy = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
        int weekNumber = localDate.get(woy);
        TurnipWeek turnipWeek = userService.getTurnipWeek(acnhUser, localDate.getYear(), weekNumber);
        turnipService.updateTurnipWeekWithLocalStorage(turnipWeek, localStorage);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
