package com.mytechuncle.acnh.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class TurnipUserGroup {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String name;
    @ManyToOne(optional = false)
    private ACNHUser admin;
    @ManyToMany
    @JoinTable(
            name = "group_users",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<ACNHUser> members;

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

    public ACNHUser getAdmin() {
        return admin;
    }

    public void setAdmin(ACNHUser admin) {
        this.admin = admin;
    }

    public List<ACNHUser> getMembers() {
        return members;
    }

    public void setMembers(List<ACNHUser> members) {
        this.members = members;
    }
}
