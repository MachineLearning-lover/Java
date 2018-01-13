package com.doctor.doctorReview.Repositories;

import com.doctor.doctorReview.Entities.Appointment;
import com.doctor.doctorReview.Entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
    Optional<Appointment> getAllByDoctor(Doctor doctor);
    Optional<Appointment> getAllByDoctorEmail(final String doctorEmail);
    Optional<Appointment> getAllByClientEmail(final String clientEmail);
}
