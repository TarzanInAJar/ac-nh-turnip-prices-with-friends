package com.mytechuncle.acnh.repositories;

import com.mytechuncle.acnh.models.TurnipWeek;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TurnipWeekRepository extends JpaRepository<TurnipWeek, Long> {
}
