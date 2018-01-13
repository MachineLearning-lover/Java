package com.doctor.doctorReview.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "review")
    private Appointment appointment;

    // from 0 to 5 stars
    private String rating;
    private String details;

    public Review(Appointment appointment, String rating, String details) {
        this.appointment = appointment;
        this.rating = rating;
        this.details = details;
    }


}
