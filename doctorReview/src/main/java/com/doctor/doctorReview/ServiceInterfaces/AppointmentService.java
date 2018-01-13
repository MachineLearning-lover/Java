package com.doctor.doctorReview.ServiceInterfaces;

import com.doctor.doctorReview.Entities.Appointment;
import com.doctor.doctorReview.Entities.Client;
import com.doctor.doctorReview.Entities.Doctor;

import java.util.Optional;

public interface AppointmentService  {

    Optional<Appointment> getAllAppointmentsByClientEmail(final String clientEmail);
    Optional<Appointment> getAllAppointmentsByDoctorEmail(final String doctorEmail);
    void saveAppointment(Doctor doctor, Client client);
    void deleteAppointment(Appointment appointment);
//    void editAppointment()

}
