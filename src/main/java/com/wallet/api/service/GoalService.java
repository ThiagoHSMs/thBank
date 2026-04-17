package com.wallet.api.service;


import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wallet.api.model.Goal;
import com.wallet.api.repository.GoalRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GoalService {
    
    private final GoalRepository goalRepository;

    public Goal save(Goal goal) {
        return goalRepository.save(goal);
    }

    public List<Goal> findAll() {
        return goalRepository.findAll();
    }

    @Transactional
    public Goal addValueToGoal(Long goalId, BigDecimal value) {
        Goal goal = goalRepository.findById(goalId).orElseThrow(() -> new RuntimeException("Meta não encontrada"));

        goal.setSavedAmount(goal.getSavedAmount().add(value));
        return goalRepository.save(goal);
    }
}
