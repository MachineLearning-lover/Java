package com.doctor.doctorReview.ServiceImpl;

import com.doctor.doctorReview.Entities.Appointment;
import com.doctor.doctorReview.Entities.Client;
import com.doctor.doctorReview.Entities.Doctor;
import com.doctor.doctorReview.Repositories.AppointmentRepository;
import com.doctor.doctorReview.Repositories.ClientRepository;
import com.doctor.doctorReview.Repositories.DoctorRepository;
import com.doctor.doctorReview.ServiceInterfaces.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Override
    public Optional<Appointment> getAllAppointmentsByClientEmail(String clientEmail) {
        return appointmentRepository.getAllByClientEmail(clientEmail);
    }

    @Override
    public Optional<Appointment> getAllAppointmentsByDoctorEmail(String doctorEmail) {
        return appointmentRepository.getAllByDoctorEmail(doctorEmail);
    }

    @Override
    public void saveAppointment(Doctor doctor, Client client) {
        clientRepository.save(client);
        doctorRepository.save(doctor);
        appointmentRepository.save(new Appointment(doctor,client));
    }

    @Override
    public void deleteAppointment(Appointment appointment) {

    }
}
