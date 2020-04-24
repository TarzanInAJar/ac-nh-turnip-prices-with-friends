package com.mytechuncle.acnh.repositories;

import com.mytechuncle.acnh.models.ACNHUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ACNHUserRepository extends JpaRepository<ACNHUser, Long> {
    public Optional<ACNHUser> findUserByEmail(String email);
}
