package com.mytechuncle.acnh.models.dto;

import com.mytechuncle.acnh.models.ACNHUser;
import com.mytechuncle.acnh.models.TurnipUserGroup;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ACNHUserDTO {
    private Long id;
    private String email;
    private String name;
    private Map<Long, String> groups;

    public static ACNHUserDTO from(ACNHUser user) {
        ACNHUserDTO dto = new ACNHUserDTO();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setName(user.getName());
        if (user.getGroups() != null) {
            dto.setGroups(user.getGroups().stream().collect(Collectors.toMap(TurnipUserGroup::getId,TurnipUserGroup::getName)));
        }

        return dto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Long, String> getGroups() {
        return groups;
    }

    public void setGroups(Map<Long, String> groups) {
        this.groups = groups;
    }


}
