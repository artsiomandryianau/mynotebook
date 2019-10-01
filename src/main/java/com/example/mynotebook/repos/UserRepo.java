package com.example.mynotebook.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.mynotebook.domain.User;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
