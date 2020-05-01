package com.mytechuncle.acnh.repositories;

import com.mytechuncle.acnh.models.TurnipUserGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TurnipUserGroupRepository extends JpaRepository<TurnipUserGroup, Long> {
    Optional<TurnipUserGroup> findByName(String name);
}
