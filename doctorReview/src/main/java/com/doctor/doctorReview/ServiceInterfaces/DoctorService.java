package com.doctor.doctorReview.ServiceInterfaces;

import com.doctor.doctorReview.Entities.Doctor;

import java.util.List;

public interface DoctorService {

     void saveDoctor(Doctor doctor);
     List<Doctor> getDoctors();
}
