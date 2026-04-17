package com.wallet.api.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wallet.api.model.Goal;
import com.wallet.api.service.GoalService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class GoalController {
    
    private final GoalService goalService;

    @GetMapping
    public List<Goal> listAll(){
        return goalService.findAll();
    }

    @PostMapping
    public Goal creat(@RequestBody Goal goal) {
        return goalService.save(goal);
    }

    @PatchMapping("/{id}/deposit")
    public Goal deposit(@PathVariable Long id, @RequestBody BigDecimal value){
        return goalService.addValueToGoal(id, value);
    }

}
