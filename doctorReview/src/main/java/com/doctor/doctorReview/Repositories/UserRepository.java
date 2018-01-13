package com.doctor.doctorReview.Repositories;

import com.doctor.doctorReview.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(final String email);
}
