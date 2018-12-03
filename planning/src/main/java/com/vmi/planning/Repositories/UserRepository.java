package com.vmi.planning.Repositories;

import com.vmi.planning.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByDetails_Email(String email);
}
