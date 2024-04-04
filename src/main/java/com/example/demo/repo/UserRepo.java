package com.example.demo.repo;

import com.example.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Long> {
    List<User> findByFirstName(String firstName);
}
