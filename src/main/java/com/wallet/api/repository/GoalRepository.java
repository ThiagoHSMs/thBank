package com.wallet.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wallet.api.model.Goal;

@Repository
public interface GoalRepository extends JpaRepository<Goal, Long> {
}
