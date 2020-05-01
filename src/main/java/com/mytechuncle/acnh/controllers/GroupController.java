package com.mytechuncle.acnh.controllers;

import com.mytechuncle.acnh.models.ACNHUser;
import com.mytechuncle.acnh.models.TurnipUserGroup;
import com.mytechuncle.acnh.models.TurnipWeek;
import com.mytechuncle.acnh.models.dto.TurnipUserGroupDTO;
import com.mytechuncle.acnh.models.dto.TurnipWeekDTO;
import com.mytechuncle.acnh.models.dto.put.TurnipUserGroupUpdateDTO;
import com.mytechuncle.acnh.repositories.ACNHUserRepository;
import com.mytechuncle.acnh.repositories.TurnipUserGroupRepository;
import com.mytechuncle.acnh.services.ACNHUserService;
import com.mytechuncle.acnh.services.TurnipDTOService;
import com.mytechuncle.acnh.services.TurnipUserGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/group")
public class GroupController {

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
    public ResponseEntity<List<TurnipUserGroupDTO>> getUserGroups(@AuthenticationPrincipal OAuth2User user) {
        ACNHUser acnhUser = userService.getUserFromOAuth(user);
        if (acnhUser.getGroups() != null) {
            return new ResponseEntity<>(acnhUser.getGroups().stream().map(TurnipUserGroupDTO::from).collect(Collectors.toList()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.OK);
        }
    }

    @PostMapping("")
    public ResponseEntity<Void> createUserGroup(@AuthenticationPrincipal OAuth2User user, String name, UriComponentsBuilder uriBuilder) {
        ACNHUser acnhUser = userService.getUserFromOAuth(user);
        TurnipUserGroup group = groupService.createGroup(name, acnhUser);
        HttpHeaders headers = new HttpHeaders();
        UriComponents uriComponents = uriBuilder.path("/group/{id}").buildAndExpand(group.getId());
        headers.setLocation(uriComponents.toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurnipUserGroupDTO> getUserGroup(@AuthenticationPrincipal OAuth2User user, @PathVariable("id") Long id) {
        Optional<TurnipUserGroup> groupOptional = turnipUserGroupRepository.findById(id);
        if (!groupOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ACNHUser acnhUser = userService.getUserFromOAuth(user);
        TurnipUserGroup group = groupOptional.get();
        if (!group.getMembers().contains(acnhUser)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(TurnipUserGroupDTO.from(group), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TurnipUserGroupDTO> updateUserGroup(@AuthenticationPrincipal OAuth2User user, @PathVariable("id") Long id, @RequestBody TurnipUserGroupUpdateDTO updatedGroup) {
        ACNHUser acnhUser = userService.getUserFromOAuth(user);
        if (!id.equals(updatedGroup.getId())){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Optional<TurnipUserGroup> groupOptional = turnipUserGroupRepository.findById(id);
        if (!groupOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        TurnipUserGroup group = groupOptional.get();
        if (!group.getAdmin().equals(acnhUser)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        groupService.updateGroup(updatedGroup);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}/turnips")
    public ResponseEntity<List<TurnipWeekDTO>> getUserGroupCurrentTurnips(@AuthenticationPrincipal OAuth2User user, @PathVariable("id") Long id) {
        ACNHUser acnhUser = userService.getUserFromOAuth(user);
        LocalDate localDate = LocalDate.now();
        TemporalField woy = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
        int weekNumber = localDate.get(woy);
        Optional<TurnipUserGroup> optionalGroup = turnipUserGroupRepository.findById(id);
        if (!optionalGroup.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        TurnipUserGroup group = optionalGroup.get();
        if (!group.getMembers().contains(acnhUser)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        List<TurnipWeek> groupWeeks = userService.getGroupTurnipWeeks(group, localDate.getYear(), weekNumber);
        return new ResponseEntity<>(groupWeeks.stream().map(TurnipWeekDTO::from).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/refreshGlobalGroup")
    public ResponseEntity<Void> refreshGlobalGroup(@AuthenticationPrincipal OAuth2User user) {
        ACNHUser acnhUser = userService.getUserFromOAuth(user);
        TurnipUserGroup group = groupService.getGlobalGroup(acnhUser); // TODO this is bad, lazy, quick and dirty code to get something working up for my friends
        group.setMembers(userRepository.findAll());
        turnipUserGroupRepository.save(group);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
