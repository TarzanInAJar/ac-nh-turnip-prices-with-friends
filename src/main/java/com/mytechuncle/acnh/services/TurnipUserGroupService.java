package com.mytechuncle.acnh.services;

import com.mytechuncle.acnh.models.ACNHUser;
import com.mytechuncle.acnh.models.TurnipUserGroup;
import com.mytechuncle.acnh.models.dto.TurnipUserGroupDTO;
import com.mytechuncle.acnh.models.dto.put.TurnipUserGroupUpdateDTO;
import com.mytechuncle.acnh.repositories.ACNHUserRepository;
import com.mytechuncle.acnh.repositories.TurnipUserGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TurnipUserGroupService {
    @Autowired
    ACNHUserRepository userRepository;

    @Autowired
    TurnipUserGroupRepository repository;

    public TurnipUserGroup createGroup(String name, ACNHUser admin) {
        if (repository.findByName(name).isPresent()) {
            throw new IllegalArgumentException("A group with name " + name + " already exists");
        }
        TurnipUserGroup turnipUserGroup = new TurnipUserGroup();
        turnipUserGroup.setAdmin(admin);
        turnipUserGroup.setName(name);
        return repository.save(turnipUserGroup);
    }

    public void updateGroup(TurnipUserGroupUpdateDTO updatedGroup) {
        validateGroupUpdateDTO(updatedGroup);
        TurnipUserGroup group = repository.findById(updatedGroup.getId()).get();
        group.setName(updatedGroup.getName());
        group.setMembers(userRepository.findAllById(updatedGroup.getMembers()));
        group.setAdmin(userRepository.findById(updatedGroup.getAdmin()).get());
        repository.save(group);
    }

    private void validateGroupUpdateDTO(TurnipUserGroupUpdateDTO group) {
        if (!repository.existsById(group.getId())) {
            throw new IllegalArgumentException("No group exists for given id " + group.getId());
        }
        if (!userRepository.existsById(group.getAdmin())) {
            throw new IllegalArgumentException("Specified admin with id " + group.getAdmin() + " does not exist");
        }
        group.getMembers().stream().forEach(memberId -> {
            if (!userRepository.existsById(memberId)) {
                throw new IllegalArgumentException("Specified member with id " + memberId + " does not exist");
            }
        });
        Optional<TurnipUserGroup> existingGroupWithName = repository.findByName(group.getName());
        if (existingGroupWithName.isPresent() && !existingGroupWithName.get().getId().equals(group.getId())) {
            throw new IllegalArgumentException("A group with name " + group.getName() + " already exists");
        }
    }
}
