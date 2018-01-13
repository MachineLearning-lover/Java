package com.doctor.doctorReview.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@DiscriminatorValue("doctor")
public class Doctor extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    protected String seniority;
    protected String hospital;
    protected Calendar consultingCalendar;

    @OneToMany(mappedBy = "doctor")
    private List<Appointment> appointments;

    @ManyToMany
    @JoinTable(name = "doctor_holiday",joinColumns ={@JoinColumn(name="doctor_id",referencedColumnName = "id")},
    inverseJoinColumns = {@JoinColumn(name="holiday_id",referencedColumnName = "id")})
    private List<Holiday> holidays;

    public Doctor(String firstName, String lastName, String middleName, String email, String passwordHash ) {
        this.FirstName = firstName;
        this.LastName = lastName;
        this.MiddleName = middleName;
        this.email = email;
        this.passwordHash = passwordHash;
//        appointments = new ArrayList<>();
    }
}
