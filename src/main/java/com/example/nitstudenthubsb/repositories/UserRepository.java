package com.example.nitstudenthubsb.repositories;




import com.example.nitstudenthubsb.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}


