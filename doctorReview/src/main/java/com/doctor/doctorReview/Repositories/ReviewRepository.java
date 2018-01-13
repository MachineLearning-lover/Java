package com.doctor.doctorReview.Repositories;

import com.doctor.doctorReview.Entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review,Long> {
}
