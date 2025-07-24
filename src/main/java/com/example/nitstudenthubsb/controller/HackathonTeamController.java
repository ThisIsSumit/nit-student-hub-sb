package com.example.nitstudenthubsb.controller;


import com.example.nitstudenthubsb.entity.User;
import com.example.nitstudenthubsb.entity.UserSearchCriteria;
import com.example.nitstudenthubsb.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hackathon")
public class HackathonTeamController {
    @Autowired
    private UserRepository userRepository;


    @PostMapping("/match")
    public List<User> findTeammates(@RequestBody UserSearchCriteria criteria) {
        return userRepository.findAll().stream()
                .filter(user -> matchesCriteria(user, criteria))
                .toList();
    }

    private boolean matchesCriteria(User user, UserSearchCriteria criteria) {
        return (criteria.getTechStack() == null || user.getTechStack().contains(criteria.getTechStack())) &&
                (criteria.getHostel() == null || user.getHostel().equalsIgnoreCase(criteria.getHostel())) &&
                (criteria.getLanguages() == null || user.getLanguages().contains(criteria.getLanguages())) &&
                (criteria.getHabits() == null || user.getHabits().contains(criteria.getHabits()));
    }
}





