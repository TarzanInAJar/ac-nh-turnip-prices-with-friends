package com.mytechuncle.acnh.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class ACNHUser {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String email;
    private String name;

    @ManyToMany(mappedBy = "members")
    private List<TurnipUserGroup> groups;

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

    public List<TurnipUserGroup> getGroups() {
        return groups;
    }

    public void setGroups(List<TurnipUserGroup> groups) {
        this.groups = groups;
    }
}
