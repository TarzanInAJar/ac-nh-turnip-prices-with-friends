package com.mytechuncle.acnh.repositories;

import com.mytechuncle.acnh.models.ACNHUser;
import com.mytechuncle.acnh.models.TurnipWeek;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TurnipWeekRepository extends JpaRepository<TurnipWeek, Long> {
    Optional<TurnipWeek> findByUserAndYearAndWeek(ACNHUser user, int year, int week);
}
