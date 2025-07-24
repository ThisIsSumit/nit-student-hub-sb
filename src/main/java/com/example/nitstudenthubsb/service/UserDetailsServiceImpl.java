package com.example.nitstudenthubsb.service;

import com.example.nitstudenthubsb.entity.User;
import com.example.nitstudenthubsb.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String rollNo) throws UsernameNotFoundException {
        User user = userRepository.findByRollNo(rollNo)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with roll number: " + rollNo));

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getRollNo())
                .password(user.getPassword())
                .authorities(new ArrayList<>()) // Add roles/authorities as needed
                .build();
    }
}
