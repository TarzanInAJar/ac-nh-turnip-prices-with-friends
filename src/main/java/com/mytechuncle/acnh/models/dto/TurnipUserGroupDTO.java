package com.mytechuncle.acnh.models.dto;

import com.mytechuncle.acnh.models.ACNHUser;
import com.mytechuncle.acnh.models.TurnipUserGroup;

import java.util.Map;
import java.util.stream.Collectors;

public class TurnipUserGroupDTO {
    private Long id;
    private String name;
    private Long admin;
    private Map<Long, String> members;

    public static TurnipUserGroupDTO from(TurnipUserGroup group) {
        TurnipUserGroupDTO dto = new TurnipUserGroupDTO();
        dto.setId(group.getId());
        dto.setName(group.getName());
        dto.setAdmin(group.getAdmin().getId());
        dto.setMembers(group.getMembers().stream().collect(Collectors.toMap(ACNHUser::getId,ACNHUser::getName)));
        return dto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAdmin() {
        return admin;
    }

    public void setAdmin(Long admin) {
        this.admin = admin;
    }

    public Map<Long, String> getMembers() {
        return members;
    }

    public void setMembers(Map<Long, String> members) {
        this.members = members;
    }
}
