package com.mytechuncle.acnh.controllers;

import com.mytechuncle.acnh.models.ACNHUser;
import com.mytechuncle.acnh.models.TurnipUserGroup;
import com.mytechuncle.acnh.models.TurnipWeek;
import com.mytechuncle.acnh.models.dto.TurnipWeekDTO;
import com.mytechuncle.acnh.models.ui.TurnipWeekLocalStorage;
import com.mytechuncle.acnh.repositories.ACNHUserRepository;
import com.mytechuncle.acnh.repositories.TurnipUserGroupRepository;
import com.mytechuncle.acnh.services.ACNHUserService;
import com.mytechuncle.acnh.services.TurnipDTOService;
import com.mytechuncle.acnh.services.TurnipUserGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/turnips")
public class TurnipWeekController {

    @Autowired
    ACNHUserService userService;

    @Autowired
    TurnipDTOService turnipService;

    @Autowired
    TurnipUserGroupService groupService;

    @Autowired
    ACNHUserRepository userRepository;

    @Autowired
    TurnipUserGroupRepository turnipUserGroupRepository;

    @GetMapping("")
    public ResponseEntity<TurnipWeekDTO> getCurrentTurnipWeek(@AuthenticationPrincipal OAuth2User user) {
        ACNHUser acnhUser = userService.getUserFromOAuth(user);
        LocalDate localDate = LocalDate.now();
        TemporalField woy = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
        int weekNumber = localDate.get(woy);
        TurnipWeek turnipWeek = userService.getTurnipWeek(acnhUser, localDate.getYear(), weekNumber);
        return new ResponseEntity<>(TurnipWeekDTO.from(turnipWeek), HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<Void> updateCurrentTurnipWeek(@AuthenticationPrincipal OAuth2User user, @RequestBody TurnipWeekLocalStorage localStorage) {
        ACNHUser acnhUser = userService.getUserFromOAuth(user);
        LocalDate localDate = LocalDate.now();
        TemporalField woy = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
        int weekNumber = localDate.get(woy);
        TurnipWeek turnipWeek = userService.getTurnipWeek(acnhUser, localDate.getYear(), weekNumber);
        turnipService.updateTurnipWeekWithLocalStorage(turnipWeek, localStorage);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
