package com.example.nitstudenthubsb.service;

import com.example.nitstudenthubsb.entity.User;

import com.example.nitstudenthubsb.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

}
