package com.doctor.doctorReview.Repositories;

import com.doctor.doctorReview.Entities.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HolidayRepository extends JpaRepository<Holiday,Long> {
}
