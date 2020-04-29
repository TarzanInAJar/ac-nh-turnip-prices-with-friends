package com.mytechuncle.acnh.models.dto.put;

import java.util.Set;

public class TurnipUserGroupUpdateDTO {
    private Long id;
    private String name;
    private Long admin;
    private Set<Long> members;

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

    public Set<Long> getMembers() {
        return members;
    }

    public void setMembers(Set<Long> members) {
        this.members = members;
    }
}
