package com.doctor.doctorReview.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Data
@NoArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="doctor_id",nullable = true)
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="client_id",nullable = true)
    private Client client;

    @OneToOne
    private Review review;

    private Calendar time;
    private String duration;
    private String motif;
    // the doctor confirms the appointment
    private Boolean isChecked;
    private String medicalRecommendations;
    private String indicatedTreatment;

    public Appointment(Doctor doctor, Client client) {
        this.doctor = doctor;
        this.client = client;
    }

    public Appointment(Doctor doctor, Client client, Calendar time, String duration, String motif) {
        this.doctor = doctor;
        this.client = client;
        this.time = time;
        this.duration = duration;
        this.motif = motif;
    }

    public Appointment(Client client){
        this.client = client;
    }

    public Appointment(Doctor doctor){
        this.doctor = doctor;
    }
}
