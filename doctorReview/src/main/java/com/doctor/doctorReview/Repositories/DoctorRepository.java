package com.doctor.doctorReview.Repositories;

import com.doctor.doctorReview.Entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor,Long> {
}
