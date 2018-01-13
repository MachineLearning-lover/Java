package com.doctor.doctorReview.ServiceImpl;

import com.doctor.doctorReview.Entities.Doctor;
import com.doctor.doctorReview.Repositories.DoctorRepository;
import com.doctor.doctorReview.ServiceInterfaces.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    DoctorRepository doctorRepository;

    @Override
    public void saveDoctor(Doctor doctor) {
        doctorRepository.save(doctor);
    }

    @Override
    public List<Doctor> getDoctors() {
        return doctorRepository.findAll();
    }
}
