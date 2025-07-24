package com.example.nitstudenthubsb.repositories;

import com.example.nitstudenthubsb.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByRollNo(String rollNo);

    Optional<User> findByUsername(String username);

    boolean existsByRollNo(String rollNo);

    boolean existsByUsername(String username);





}


