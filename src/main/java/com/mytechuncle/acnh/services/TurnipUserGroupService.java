package com.mytechuncle.acnh.services;

import com.mytechuncle.acnh.models.ACNHUser;
import com.mytechuncle.acnh.models.TurnipUserGroup;
import com.mytechuncle.acnh.repositories.TurnipUserGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TurnipUserGroupService {
    @Autowired
    TurnipUserGroupRepository repository;

    public TurnipUserGroup createGroup(String name, ACNHUser admin) {
        TurnipUserGroup turnipUserGroup = new TurnipUserGroup();
        turnipUserGroup.setAdmin(admin);
        turnipUserGroup.setName(name);
        return repository.save(turnipUserGroup);
    }

    public TurnipUserGroup createOrReturnGroup(String name, ACNHUser admin) {
        Optional<TurnipUserGroup> userGroupOptional = repository.findByName(name);
        if (userGroupOptional.isPresent()) {
            return userGroupOptional.get();
        } else {
            TurnipUserGroup userGroup = new TurnipUserGroup();
            userGroup.setName(name);
            userGroup.setAdmin(admin);
            return repository.save(userGroup);
        }
    }

}
